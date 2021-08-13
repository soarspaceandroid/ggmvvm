package com.pajk.healthmanageapp.base.bridge

import android.content.Context

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/29
 *※ Time : 15:27
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.bridge
 *----------------------------------------------------
 */
class DiplomacyCreater {

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DiplomacyCreater()
        }
    }

    val WORKERS_NET = "worker_net"
    val WORKERS_BUSNIESS = "worker_busniess"

    private var workers :HashMap<String , Diplomacy>  = HashMap<String ,Diplomacy>()

    /**
     * 添加外交实现类
     */
    fun addWorkers(vararg worker:Diplomacy){
        for (item in worker){
            when(item){
                is DiplomacyBusniss ->{
                    workers[WORKERS_BUSNIESS] = item
                }
                is DiplomacyNet ->{
                    workers[WORKERS_NET] = item
                }
            }
        }
    }




    /**
     * 获取实现类
     */
    fun getDiplomacyBusniss() = (workers[WORKERS_BUSNIESS]?:object :DiplomacyBusniss{
    } ) as DiplomacyBusniss

    /**
     * 获取实现类
     */
    fun getDiplomacyNet() = (workers[WORKERS_NET] ?:object :DiplomacyNet{
        override fun <T> sendRequest(
            context: Context,
            s: String,
            map: Map<String, String>,
            i: Int,
            aClass: Class<T>,
            onNetResponseListener: OnNetResponseListener<T>
        ) {
        }
    } ) as DiplomacyNet



}