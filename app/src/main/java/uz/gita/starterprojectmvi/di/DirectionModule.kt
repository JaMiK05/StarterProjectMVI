package uz.gita.starterprojectmvi.di

import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.starterprojectmvi.presentation.screen.auth.login.*
import uz.gita.starterprojectmvi.presentation.screen.auth.register.*
import uz.gita.starterprojectmvi.presentation.screen.deteils.*
import uz.gita.starterprojectmvi.presentation.screen.home.*
import uz.gita.starterprojectmvi.presentation.screen.home.page1.*
import uz.gita.starterprojectmvi.presentation.screen.home.page2.*
import uz.gita.starterprojectmvi.presentation.screen.home.page3.*
import uz.gita.starterprojectmvi.presentation.screen.payments.*
import uz.gita.starterprojectmvi.presentation.screen.search.*
import uz.gita.starterprojectmvi.presentation.screen.startviewpager.intro.*
import uz.gita.starterprojectmvi.presentation.screen.startviewpager.splash.*
import uz.gita.starterprojectmvi.presentation.screen.yourcourses.*

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindIntroDirection(impl: IntroDirectionImpl): IntroDirection

    @Binds
    fun bindDeteilsDirection(impl: DetailsDirections): DetailsContract.Directions

    @Binds
    fun bindPAymentDirection(impl: PaymentDirectionImpl): PaymentDirection

    @Binds
    fun bindYourCoursesDirection(impl: YourCoursesDirectionImpl): YourCoursesDirection

    @[Binds]
    fun bindSplashDirection(impl: SplashDirectionImpl): SplashDirection

    @Binds
    fun bindHomeDirection(impl: HomeDirectionImpl): HomeDirection

    @Binds
    fun bindSearchDirection(impl: SearchDirectionsImpl): SearchContract.Directions

    @Binds
    fun bindPage1Direction(impl: Page1DirectionImpl): Page1Contract.Page1Direction

    @Binds
    fun bindPage2Direction(impl: Page2DirectionImpl): Page2Direction

    @Binds
    fun bindPage3Direction(impl: Page3DirectionImpl): Page3Direction

    @Binds
    fun bindLoginDirection(impl: LoginDirectionImpl): LoginDirection

    @Binds
    fun bindRegisterDirection(impl: RegisterDirectionImpl): RegisterDirection
}