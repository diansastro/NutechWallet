package com.widi.nutechwallet.di.builder

import com.widi.nutechwallet.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity
}