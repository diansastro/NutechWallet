package com.widi.nutechwallet.view.profile

import com.widi.nutechwallet.base.ErrorView
import com.widi.nutechwallet.data.body.UpdateProfileBody
import com.widi.nutechwallet.data.response.RegisteredUserResponse

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

interface ProfileContract {
    interface View: ErrorView {
        fun getProfile(registeredUserResponse: RegisteredUserResponse?)
        fun onSuccess()
        fun onError()
    }

    interface Presenter {
        fun execGetProfile()
        fun execUpdateProfile(updateProfileBody: UpdateProfileBody)
    }
}