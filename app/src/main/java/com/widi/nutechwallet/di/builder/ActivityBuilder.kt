package com.widi.nutechwallet.di.builder

import com.widi.nutechwallet.view.home.HomeActivity
import com.widi.nutechwallet.view.login.LoginActivity
import com.widi.nutechwallet.view.profile.ProfileActivity
import com.widi.nutechwallet.view.register.RegisterActivity
import com.widi.nutechwallet.view.splash.SplashActivity
import com.widi.nutechwallet.view.topup.TopUpActivity
import com.widi.nutechwallet.view.transfer.TransferActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindProfileActivity(): ProfileActivity

    @ContributesAndroidInjector
    abstract fun bindTransferActivity(): TransferActivity

    @ContributesAndroidInjector
    abstract fun bindTopUpActivity(): TopUpActivity
}