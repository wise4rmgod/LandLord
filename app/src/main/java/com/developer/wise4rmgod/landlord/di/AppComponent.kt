package com.developer.wise4rmgod.landlord.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ActivitiesModule::class
    ]
)
interface AppComponent {

    // store all Dagger 2 setup and configuration code under an injection package.
    //
    //Create the AppComponent class under injection package. A component is used to initialize modules.
    // Dagger 2 will auto-generate DaggerAppComponent which is used for initialization at Application.

    @Component.Builder
    interface Builder {
        // provide Application instance into DI
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: Application)
}