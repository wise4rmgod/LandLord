package com.developer.wise4rmgod.landlord.di

import com.developer.wise4rmgod.landlord.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    // All activities intended to use Dagger2 @Inject should be listed here.
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}