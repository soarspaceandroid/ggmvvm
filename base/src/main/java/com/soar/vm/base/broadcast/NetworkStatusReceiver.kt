package com.soar.vm.base.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.soar.vm.base.datatype.NetState
import com.soar.vm.base.datatype.CommonData
import com.soar.vm.base.extend.hmlog

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 14:51
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.broadcast
 *----------------------------------------------------
 */
class NetworkStatusReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        hmlog("onreceive ----- "+intent.action)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //移动数据
        val mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        //wifi网络
        val wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        if (mobNetInfo != null && !mobNetInfo.isConnected && wifiNetInfo != null && !wifiNetInfo.isConnected) { //网络状态全部不可用
            hmlog("无网络连接")
            CommonData.INSTANCE.netState?.value = NetState.NONE
            return
        }
        if (wifiNetInfo != null && wifiNetInfo.isConnected) {
            hmlog("wifi网络连接")
            CommonData.INSTANCE.netState?.value = NetState.WIFI
            return
        }


        if (mobNetInfo != null && mobNetInfo.isConnected && wifiNetInfo != null && !wifiNetInfo.isConnected) { //            手机没有处于wifi网络而是处于移动网络
            hmlog("移动网络连接")
            CommonData.INSTANCE.netState?.value = NetState.MOBILE

        }
    }

}