package com.soar.vm.base.model

import androidx.lifecycle.*
import com.soar.vm.base.datatype.CommonData
import com.soar.vm.base.extend.bindUI
import com.soar.vm.base.interfaces.CallBridge
import com.soar.vm.base.repository.BaseRepository
import kotlinx.android.synthetic.main.layout_base_view.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 9:39
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.model
 *----------------------------------------------------
 */
open class BaseViewModel:ViewModel(),CallBridge<ActionData<Any>>{



    /**
     * 对应的数据变更
     */
    val actionData by lazy { MutableLiveData<ActionData<*>>() }


    /**
     * view model 对应的lifeownner
     * 监听actionData改变
     */
    var callBridge:CallBridge<ActionData<*>>?  = null
    set(value) {
        field = value
        (callBridge as LifecycleOwner).let {
            actionData.bindUI(it){action->
                paraseActionData(action)
            }
            CommonData.INSTANCE.eventData.bindUI(it){action->
                paraseActionData(action)
            }
        }


    }


    /**
     * 数据来源Repository
     */
    protected var repository  = createRepository().apply {
        callBridge = this@BaseViewModel as CallBridge<ActionData<*>>
    }

    /**
     * 使用之前重写,返回子类的Repository
     */
     protected open fun createRepository(): BaseRepository {
         return BaseRepository()
     }

    /**
     * 改变actiondata 的值
     */
    override fun obtain(t: ActionData<Any>) {
        actionData.value = t
    }

    /**
     * 子类调用改变actionData的值
     */
    fun changeActionData(t: ActionData<*>){
        actionData.value = t
    }


    /**
     * 分析actiondata的改变后的值,无需UI处理的,直接此处处理 ,如果需要UI处理的直接传递给UI继续处理
     */
    private fun paraseActionData(actionData: ActionData<*>){
        when(actionData.action){
            CallAction.ACTION_EXCEPTION ,CallAction.ACTION_SHOWLOADING,CallAction.ACTION_HIDELOADING ->{
                //各种异常情况 传递给UI处理
                callBridge?.obtain(actionData)
            }
            CallAction.ACTION_EVENT->{
                //发送过来的消息处理,数据更新类消息当前类处理,UI更新类消息UI处理,都重写onEvent
                actionData.t?.let {
                    onEvent(it)
                }
                callBridge?.obtain(actionData)
            }
            CallAction.ACTION_RESPONSE ->{
                //网络数据来源
                actionData.t?.let {
                    onRequestSuccess(it)
                }
            }
        }
    }

    /**
     * 请求成功的数据,子viewmodel重写,解析并赋值
     */
    open fun onRequestSuccess(t:Any){

    }

    /**
     * 消息处理
     */
    open fun onEvent(t:Any){

    }

}