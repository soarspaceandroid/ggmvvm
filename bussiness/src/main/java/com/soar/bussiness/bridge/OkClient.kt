package com.soar.bussiness.bridge

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 17:10
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.http
 *----------------------------------------------------
 */
class OkClient {


    //测试请求
    //https://autumnfish.cn/search?keywords=%E7%88%B1

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkClient()
        }
    }

    private val TIME_OUT = 60 //30s
    private var okHttpClient:OkHttpClient? = null

    constructor(){
        val builder = OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .hostnameVerifier { hostname: String?, session: SSLSession? -> true } //Maxidleconnects解决上传弱网超时问题,默认5,OkhttpManager中也是5
            .connectionPool(ConnectionPool(30, TIME_OUT.toLong(), TimeUnit.SECONDS))
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okHttpClient = builder.build()
    }



    /**
     * get client
     * @return
     */
    fun getClient(): OkHttpClient? {
        return okHttpClient
    }
}