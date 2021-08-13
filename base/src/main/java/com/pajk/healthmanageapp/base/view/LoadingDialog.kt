package com.pajk.healthmanageapp.base.view

import com.pajk.healthmanageapp.base.R
import kotlinx.android.synthetic.main.layout_loading.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 10:43
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.view
 *----------------------------------------------------
 */
class LoadingDialog:BaseDialog() {

    override fun getLayoutId(): Int {
        return R.layout.layout_loading
    }

    override fun initView() {
        loadingText.text = "请稍后..."
    }

}