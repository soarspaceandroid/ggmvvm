package com.pajk.healthmanageapp.base.bridge

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/29
 *※ Time : 15:53
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.bridge
 *----------------------------------------------------
 * 承接网络外交接口
 */
interface OnNetResponseListener<T> {
    fun onComplete(
        isOK: Boolean,
        t: T?,
        code: Int,
        message: String?
    )

    fun onInernError(code: Int, message: String?)
}