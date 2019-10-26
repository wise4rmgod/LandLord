package com.developer.wise4rmgod.landlord.di

import android.app.Application
import dagger.Provides

internal class AppModule(private val application: Application) {

    // AppModule shall be used initialize objects used across our application,
    // such as Room(Database), Retrofit, Shared Preference, etc.
    @Provides
    internal fun provideApplication(): Application {
        return application
    }
}