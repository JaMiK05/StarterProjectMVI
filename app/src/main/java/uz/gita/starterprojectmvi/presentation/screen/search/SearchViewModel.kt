package uz.gita.starterprojectmvi.presentation.screen.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import uz.gita.starterprojectmvi.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val directions: SearchContract.Directions,
    private val useCase: UseCase
) : SearchContract.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(SearchContract.UiState())

    init {
        eventDispatcher(SearchContract.Intent.Load)
    }

    @SuppressLint("LogNotTimber")
    override fun eventDispatcher(intent: SearchContract.Intent) {
        when (intent) {
            SearchContract.Intent.BackToHome -> {
                viewModelScope.launch {
                    directions.backToHome()
                }
            }

            SearchContract.Intent.Load -> {
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
            }

            is SearchContract.Intent.Search -> {
                useCase.getCoursesByCategory(intent.search, emptyList())
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

            is SearchContract.Intent.OpenDetailsScreen -> {
                viewModelScope.launch {
                    directions.openDetailsScreen(intent.courseData)
                }
            }
        }
    }
}