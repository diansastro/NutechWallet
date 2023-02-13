package com.widi.nutechwallet.view.register

import com.widi.nutechwallet.base.BasePresenter
import com.widi.nutechwallet.data.body.RegistBody
import com.widi.nutechwallet.data.entity.GeneralEntity
import com.widi.nutechwallet.objects.NetworkCode
import javax.inject.Inject

/**
 * Created by widi (widiytk@gmail.com) on 12/02/23.
 **/

class RegisterPresenter @Inject constructor(val generalEntity: GeneralEntity): BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

    override fun execRegist(registBody: RegistBody) {
        addSubscription(generalEntity.execRegist({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.onNextScreen()
                else -> {
                    view?.onError()
                }
            }
        }, {
           view?.onError()
        }, {}, registBody))
    }
}