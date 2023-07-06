package uz.gita.starterprojectmvi.presentation.screen.home.page1

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.starterprojectmvi.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class Page1ViewModel @Inject constructor(
    private val useCase: UseCase,
    private val direction: Page1Contract.Page1Direction,
) : Page1Contract.Model, ViewModel() {

    override val uiState = MutableStateFlow(Page1Contract.UiState())

    override val container =
        container<Page1Contract.UiState, Page1Contract.SideEffect>(Page1Contract.UiState())

    init {
        eventDispatcher(Page1Contract.Intent.Load)
    }

    @SuppressLint("LogNotTimber")
    override fun eventDispatcher(intent: Page1Contract.Intent) {
        val list = arrayListOf<String>()

        when (intent) {
            Page1Contract.Intent.Load -> {
                viewModelScope.launch {
                    useCase.getCoursesByCategory("", emptyList()).onEach { result ->
                        result.onSuccess { list ->
                            uiState.update {
                                it.copy(courses = list)
                            }
                        }
                        result.onFailure {
                            Log.d("EEE", it.message!!)
                        }
                    }.launchIn(viewModelScope)


                    useCase.getCategories().onEach { result ->
                        result.onSuccess { list ->
                            uiState.update {
                                it.copy(categories = list)
                            }
                        }
                        result.onFailure {
                            Log.d("EEE", it.message!!)
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is Page1Contract.Intent.OpenDetailsScreen -> {
                viewModelScope.launch {
                    direction.openDetailsScreen(intent.coursesData)
                }
            }

            is Page1Contract.Intent.Search -> {
                useCase.getCoursesByCategory(intent.search, list)
                    .onEach { result ->
                        result.onSuccess { list ->
                            uiState.update {
                                it.copy(courses = list)
                            }
                        }
                        result.onFailure {
                            Log.d("EEE", it.message!!)
                        }
                    }.launchIn(viewModelScope)
            }

            is Page1Contract.Intent.SelectCategories -> {
                useCase.getCoursesByCategory("", intent.list).onEach { result ->
                    result.onSuccess { list ->
                        uiState.update {
                            it.copy(courses = list)
                        }
                    }
                    result.onFailure {
                        Log.d("EEE", it.message!!)
                    }
                }.launchIn(viewModelScope)
            }

            Page1Contract.Intent.OpenSearchScreen -> {
                viewModelScope.launch {
                    direction.openSearchScreen()
                }
            }
        }
    }
}