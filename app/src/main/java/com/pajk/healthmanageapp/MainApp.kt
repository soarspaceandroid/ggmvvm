package com.pajk.healthmanageapp

import com.pajk.bussiness.bridge.DiplomacyBusnissImp
import com.pajk.bussiness.bridge.DiplomacyNetImp
import com.pajk.healthmanageapp.base.bridge.DiplomacyBusniss
import com.pajk.healthmanageapp.base.bridge.DiplomacyCreater
import com.pajk.healthmanageapp.base.bridge.DiplomacyNet
import com.pajk.healthmanageapp.base.view.BaseApplication

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/21
 *※ Time : 16:17
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp
 *----------------------------------------------------
 */
class MainApp: BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        /**
         * 网络业务调用实现
         */
        DiplomacyCreater.INSTANCE.apply {
            addWorkers(DiplomacyNetImp() ,DiplomacyBusnissImp())
        }
    }


    override fun getDataBaseName(): String  = "hmapp"
}