package uz.gita.starterprojectmvi.di

import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.starterprojectmvi.domain.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindUseCase(impl: UseCaseImpl): UseCase
}