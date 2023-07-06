package uz.gita.starterprojectmvi.domain.repository.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email:String, password:String):Flow<Result<Unit>>
    fun createUser(email: String, password: String, name: String): Flow<Result<Unit>>
    fun getUserName(email:String):Flow<Result<String>>
}