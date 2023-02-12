package com.widi.nutechwallet.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun applicationContext(application: Application): Context = application
}