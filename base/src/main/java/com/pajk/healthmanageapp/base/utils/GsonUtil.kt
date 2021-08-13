package com.pajk.healthmanageapp.base.utils

import com.google.gson.Gson

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/28
 *※ Time : 9:26
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.utils
 *----------------------------------------------------
 */
class GsonUtil {

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Gson()
        }
    }
}