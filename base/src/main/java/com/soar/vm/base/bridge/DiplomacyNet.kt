package com.soar.vm.base.bridge

import android.content.Context

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/29
 *※ Time : 15:28
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.bridge
 *----------------------------------------------------
 * 网络外交对外接口, 此接口三方库或是宿主实现 ,后面增加文件上传 下载等
 */
interface DiplomacyNet :Diplomacy{

    fun <T> sendRequest(context: Context, s:String, map:Map<String, String>, i:Int, aClass:Class<T> , onNetResponseListener: OnNetResponseListener<T> )

}