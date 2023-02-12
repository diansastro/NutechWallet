package com.widi.nutechwallet.di.component

import android.app.Application
import com.widi.nutechwallet.NutechWallet
import com.widi.nutechwallet.di.builder.ActivityBuilder
import com.widi.nutechwallet.di.module.ApplicationModule
import com.widi.nutechwallet.di.module.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, ActivityBuilder::class, AndroidInjectionModule::class])
interface ApplicationComponent {
    fun Inject(nutechWallet: NutechWallet)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}