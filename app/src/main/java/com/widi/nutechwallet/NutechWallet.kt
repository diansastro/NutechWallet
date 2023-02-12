package com.widi.nutechwallet

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.widi.nutechwallet.di.component.ApplicationComponent
import com.widi.nutechwallet.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

open class NutechWallet : MultiDexApplication(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
        JodaTimeAndroid.init(this)
    }

    fun initInjection() {
        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
        applicationComponent.Inject(this)
    }
}