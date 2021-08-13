package com.soar.vm

import com.soar.bussiness.bridge.DiplomacyBusnissImp
import com.soar.bussiness.bridge.DiplomacyNetImp
import com.soar.vm.base.bridge.DiplomacyCreater
import com.soar.vm.base.view.BaseApplication

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