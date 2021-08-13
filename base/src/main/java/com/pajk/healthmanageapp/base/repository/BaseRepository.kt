package com.pajk.healthmanageapp.base.repository

import android.service.autofill.Dataset
import com.pajk.healthmanageapp.base.bridge.DiplomacyCreater
import com.pajk.healthmanageapp.base.bridge.OnNetResponseListener
import com.pajk.healthmanageapp.base.extend.appContext
import com.pajk.healthmanageapp.base.extend.runThreadUI
import com.pajk.healthmanageapp.base.interfaces.CallBridge
import com.pajk.healthmanageapp.base.model.ActionData
import com.pajk.healthmanageapp.base.model.CallAction
import com.pajk.healthmanageapp.base.repository.preference.DataStoreServer
import com.pajk.healthmanageapp.base.repository.room.ILocalRepository
import com.pajk.healthmanageapp.base.repository.web.IRemoteRepository

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 16:30
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.repository
 *----------------------------------------------------
 * 数据来源处理类,来源有数据库和网络,网络此处统一写,数据库,宿主写
 */
open class BaseRepository : IRemoteRepository ,ILocalRepository{

    var callBridge: CallBridge<ActionData<*>>? = null

    override fun <T> postRequest(showLoadig: Boolean, clz: Class<T>, params :Map<String , String>){
        //显示loading
        changeActionData(ActionData(CallAction.ACTION_SHOWLOADING , null))

        DiplomacyCreater.INSTANCE.getDiplomacyNet()?.sendRequest(appContext , "test" ,params ,100 ,clz ,object :OnNetResponseListener<T>{
            override fun onComplete(isOK: Boolean, t: T?, code: Int, message: String?) {

                if(isOK){
                    runThreadUI {
                        //取消loading显示
                        changeActionData(ActionData(CallAction.ACTION_RESPONSE ,t))
                        changeActionData(ActionData(CallAction.ACTION_HIDELOADING , null))
                    }
                }
            }

            override fun onInernError(code: Int, message: String?) {
                changeActionData(ActionData(CallAction.ACTION_EXCEPTION, message))
            }
        })

    }

    /**
     * 此处实现一个从键值对存储中取值的实现
     */
    override fun <T> loadDataFromDB(clz: Class<T>): T? {
        return DataStoreServer.INSTANCE.getValue(clz.simpleName ,clz)
    }


    /**
     * 插入数据到数据库
     */
    override fun <T:Any> insertDataToDB(t: T?) {
        t?.let {
            DataStoreServer.INSTANCE.putValue(it::class.java.simpleName, t)
        }
    }


    override fun <T:Any> delDataFromDB(t: T) {
        t?.let {
            DataStoreServer.INSTANCE.delValue<T>(it::class.java.simpleName)
        }
    }

    /**
     * 从 repository 传值传入 viewmodel或是view
     */
    fun changeActionData(actionData: ActionData<*>){
        callBridge?.obtain(actionData)
    }


}