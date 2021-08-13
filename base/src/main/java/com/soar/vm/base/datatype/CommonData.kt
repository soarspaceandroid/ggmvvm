package com.soar.vm.base.datatype

import androidx.lifecycle.MutableLiveData
import com.soar.vm.base.model.ActionData

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 15:06
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.datatype
 *----------------------------------------------------
 * 全局共享数据,比如更新什么的,可用作Eventbus
 */
class CommonData {


    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommonData()
        }
    }

    /**
     * 网络变化值
     * 1-> no net
     * 2 ->
     */
    val netState by lazy { MutableLiveData<NetState>() }


    /**
     * Event消息通知
     */
    val eventData by lazy { MutableLiveData<ActionData<*>>() }



}