package com.soar.vm.base.repository.preference

import androidx.room.Room
import com.soar.vm.base.extend.appContext
import com.soar.vm.base.extend.runThreadIO
import com.soar.vm.base.utils.GsonUtil
import kotlinx.coroutines.runBlocking
import java.lang.Exception

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/27
 *※ Time : 10:36
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.repository.preference
 *----------------------------------------------------
 * shareperference l类似 ,键值对存储
 * 由于官方DataStore和Fragmentx冲突,并且才开始起步,放弃官方,采用数据库封装键值存储
 */
class DataStoreServer {

    private var db :KeyValueStore ? = null

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DataStoreServer() }
    }

    constructor(){
        db =  Room.databaseBuilder(
            appContext,
            KeyValueStore::class.java, "keyvalue_store"
        ).build()
        db?.isOpen
    }


    fun <T> putValue(key:String , value:T){
        runThreadIO {
            val valueStr = db?.keyValueStoreDao()?.get(key)
            if(valueStr.isNullOrEmpty()){
                db?.keyValueStoreDao()?.put(key, GsonUtil.INSTANCE.toJson(value))
            }else{
                db?.keyValueStoreDao()?.update(key, GsonUtil.INSTANCE.toJson(value))
            }

        }
    }

    /**
     * 获取music
     */
    fun <T> getValue(key:String , clz:Class<T>):T?{
        val t =  runBlocking {
            runThreadIO<T> {
                var value :T? = null
                try {
                    value = GsonUtil.INSTANCE.fromJson(db?.keyValueStoreDao()?.get(key)!!, clz)
                }catch (e:Exception){
                    e.printStackTrace()
                }
                value
            }
        }
        return t
    }



    /**
     * del value
     */
    fun <T> delValue(key:String){
        runThreadIO{
            try {
                db?.keyValueStoreDao()?.delete(key)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

