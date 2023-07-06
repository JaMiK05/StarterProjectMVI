package uz.gita.starterprojectmvi.presentation.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.starterprojectmvi.domain.repository.auth.AuthRepository

@HiltViewModel
class LoginModel @Inject constructor(
    private val direction: LoginDirection,
    private val repository: AuthRepository
):ViewModel(), LoginContract.Model {
    override val container = container<LoginContract.UiState, LoginContract.SideEffect>(
        LoginContract.UiState.Welcome)

    override fun eventDispatcher(intent: LoginContract.Intent) {
        when(intent){
            is LoginContract.Intent.LoginUser -> {
                intent{
                    reduce {
                        LoginContract.UiState.Loading
                    }
                }
                repository.loginUser(intent.email, intent.password).onEach {
                    it.onSuccess {
                        intent{
                            reduce {
                                LoginContract.UiState.Welcome
                            }
                        }
                        direction.openHomeScreen()
                    }
                    it.onFailure {
                        intent {
                            postSideEffect(LoginContract.SideEffect.ErrorWrongLoginOrPassword(it.message.toString()))
                            reduce {
                                LoginContract.UiState.Welcome
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }
            LoginContract.Intent.OpenSignUp -> {
                viewModelScope.launch {
                    direction.openRegisterScreen()
                }
            }
        }
    }
}