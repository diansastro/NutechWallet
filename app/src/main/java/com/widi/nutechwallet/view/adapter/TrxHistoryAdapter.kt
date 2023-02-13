package com.widi.nutechwallet.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.widi.nutechwallet.R
import com.widi.nutechwallet.model.TrxData
import kotlinx.android.synthetic.main.item_trx.view.*

/**
 * Created by widi (widiytk@gmail.com) on 13/02/23.
 **/

 class TrxHistoryAdapter(var trxDataList: ArrayList<TrxData>): BaseQuickAdapter<TrxData, BaseViewHolder>(R.layout.item_trx, trxDataList) {

    override fun convert(helper: BaseViewHolder?, item: TrxData?) {
        helper?.let { h ->
            item?.let { i ->
                h.itemView.tvTrxType.text = i.transaction_type
                h.itemView.tvTrxAmount.text = i.amount.toString()
            }
        }
    }
}