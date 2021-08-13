package com.soar.vm.base.extend

import android.app.Activity
import android.app.Service
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.soar.vm.base.BuildConfig
import com.soar.vm.base.exception.ContextUseException

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/21
 *※ Time : 16:51
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.utils
 *----------------------------------------------------
 */

/**
 * log 方法, 传入msg 允许传入为空
 */
fun <T> T.hmlog(vararg msg:Any?):T{
    if(BuildConfig.DEBUG){
        var str = toString()
        msg?.forEach {
            str += it.toString()
        }
        Log.e("log-health" ,str)
    }
    return this
}


fun <T> T.showToast(message:String){
    hmlog("显示了toast -> $message")
    when(this){
        is Activity ->{
            Toast.makeText(this , message , Toast.LENGTH_LONG).show()
        }
        is Fragment ->{
            Toast.makeText(requireContext() , message , Toast.LENGTH_LONG).show()
        }
        is Service ->{
            Toast.makeText(this , message , Toast.LENGTH_LONG).show()
        }
        is Context ->{
            Toast.makeText(this , message , Toast.LENGTH_LONG).show()
        }
        is View ->{
            Toast.makeText(context , message , Toast.LENGTH_LONG).show()
        }
        else -> throw ContextUseException("请在Context的作用下调用")
    }
}

