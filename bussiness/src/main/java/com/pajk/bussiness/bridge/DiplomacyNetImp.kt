package com.pajk.bussiness.bridge

import android.content.Context
import com.pajk.healthmanageapp.base.bridge.DiplomacyNet
import com.pajk.healthmanageapp.base.bridge.OnNetResponseListener
import com.pajk.healthmanageapp.base.extend.hmlog
import com.pajk.healthmanageapp.base.extend.runThreadUI
import com.pajk.healthmanageapp.base.utils.GsonUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/29
 *※ Time : 15:39
 *※ Project : hmapp-android
 *※ Package : com.pajk.bussiness.bridge
 *----------------------------------------------------
 * 网络外交的实现 只是写着测试
 */
class DiplomacyNetImp: DiplomacyNet {

    override fun <T> sendRequest(
        context: Context,
        s: String,
        map: Map<String, String>,
        i: Int,
        aClass: Class<T>,
        onNetResponseListener: OnNetResponseListener<T>
    ) {
//        ServiceManager.get().networkService.sendRequest(context ,s , map , i ,aClass ,object :NetworkService.OnResponseListener<T>{
//            override fun onComplete(p0: Boolean, p1: T?, p2: Int, p3: String?) {
//                onNetResponseListener.onComplete(p0 , p1 , p2 , p3)
//            }
//
//            override fun onInernError(p0: Int, p1: String?) {
//                onNetResponseListener.onInernError(p0 , p1 )
//            }
//        })


        val url = "https://autumnfish.cn/search?keywords=%E4%BA%86%E5%8D%B4"
        val request: Request = Request.Builder()
            .url(url)
            .get() //默认就是GET请求，可以不写
            .build()
        val call: Call = OkClient.INSTANCE.getClient()?.newCall(request)!!
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                hmlog(e?.message)
                runThreadUI {
                    onNetResponseListener.onInernError(1002 , "FAIL TO GET DATA FROM WEB")
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
//                hmlog(response.body().string())
                val result = response.body().string()
                runThreadUI {
                    //取消loading显示
                    onNetResponseListener.onComplete(true , GsonUtil.INSTANCE.fromJson(result, aClass) ,1000 , "success" )
                }

            }
        })

    }

}