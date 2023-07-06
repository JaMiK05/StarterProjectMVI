package uz.gita.starterprojectmvi.domain.repository.auth

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.ui.text.toLowerCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val pref: SharedPreferences,
) : AuthRepository {
    private val scope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
    private val auth = Firebase.auth
    private val store = Firebase.firestore

    override fun loginUser(email: String, password: String): Flow<Result<Unit>> = callbackFlow {
        val mail = email.toLowerCase()
        auth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                getUserName(mail).onEach { result ->
                    result.onSuccess {
                        pref.edit().putString("name", it).apply()
                    }
                    delay(2000)
                    pref.edit().putBoolean("HAS_USER", false).apply()
                    pref.edit().putString("EMAIL", mail).apply()
                    pref.edit().putString("PASSWORD", password).apply()
                    trySend(Result.success(Unit))
                }.launchIn(scope)
            }
            .addOnFailureListener {
                trySend(Result.failure(Exception("Login yoki parolni xato kiritdingiz!!!")))
            }
            .addOnCanceledListener {
                trySend(Result.failure(Exception("XATOLIK!!!")))
            }
        awaitClose()
    }

    @SuppressLint("DefaultLocale")
    override fun createUser(email: String, password: String, name: String): Flow<Result<Unit>> =
        callbackFlow {
            val mail = email.toLowerCase()
            auth.createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener {
                    pref.edit().putBoolean("HAS_USER", true).apply()
                    pref.edit().putString("EMAIL", mail).apply()
                    pref.edit().putString("PASSWORD", password).apply()
                    addUserToStore(name, mail)
                    trySend(Result.success(Unit))
                }
                .addOnFailureListener {
                    trySend(Result.failure(Exception("Bu akkaunt oldindan mavjud!!!")))
                }
                .addOnCanceledListener {
                    trySend(Result.failure(Exception("User yaratilmadi")))
                }
            awaitClose { }
        }

    @SuppressLint("CommitPrefEdits", "LogNotTimber")
    override fun getUserName(email: String): Flow<Result<String>> = callbackFlow {

        Log.d("LLL", "my email $email -> ${email.length}")
        store.collection("users")
            .document(email)
            .get()
            .addOnSuccessListener {
                Log.d("LLL", it.get("name") as String)
                trySend(Result.success(it.get("name") as String))
            }.addOnFailureListener {
                Log.d("LLL", it.message!!)
                trySend(Result.success("Guest"))
            }
        awaitClose()
    }

    private fun addUserToStore(name: String, email: String) {
        store.collection("users").document(email).set(hashMapOf("name" to name))
    }
}