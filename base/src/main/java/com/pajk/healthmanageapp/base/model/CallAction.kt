package com.pajk.healthmanageapp.base.model

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/28
 *※ Time : 16:39
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.interfaces
 *----------------------------------------------------
 */
object CallAction {
    /**
     * 显示loading
     */
    const val ACTION_SHOWLOADING = 10001

    /**
     * hideloading
     */
    const val ACTION_HIDELOADING = 10002

    /**
     * 异常 action
     */
    const val ACTION_EXCEPTION = 10003

    /**
     * 网络返回的Action
     */
    const val ACTION_RESPONSE = 10004


    /**
     * 类似eventBus 消息机制
     */
    const val ACTION_EVENT = 10005

}