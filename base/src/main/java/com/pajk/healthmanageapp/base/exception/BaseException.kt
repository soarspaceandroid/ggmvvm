package com.pajk.healthmanageapp.base.exception

import java.lang.IllegalStateException

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/21
 *※ Time : 16:58
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base
 *----------------------------------------------------
 */
open class BaseException(var msg:String):IllegalStateException(msg) {
}