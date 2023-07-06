package uz.gita.starterprojectmvi.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideElements {

 /*   @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): MyDatabase = Room.databaseBuilder(
        context,
        MyDatabase::class.java, MyDatabase.NAME_DB
    )
        .allowMainThreadQueries()
        .build()

    @[Provides Singleton]
    fun provideContactDao(db: MyDatabase): ProductDao = db.getProductDao()*/

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("CURRENT_USER", Context.MODE_PRIVATE)
}