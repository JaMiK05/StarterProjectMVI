package uz.gita.starterprojectmvi.di

import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.starterprojectmvi.domain.repository.*
import uz.gita.starterprojectmvi.domain.repository.auth.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @[Binds Singleton]
    fun bindRepository(impl: AuthRepositoryImpl): AuthRepository
}