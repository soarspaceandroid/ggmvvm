package com.soar.vm.base.interfaces

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/28
 *※ Time : 16:21
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.model
 *----------------------------------------------------
 */
interface CallBridge<T> {
    fun obtain(t:T)
}