package com.soar.vm.repository

import com.soar.vm.base.repository.BaseRepository
import com.soar.vm.model.Music


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 17:50
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp
 *----------------------------------------------------
 * 继承本地数据接口ILocalRepository
 */
class MusicRepository: BaseRepository(){


    fun loadData():Music?{
//        return DBManager.INSTANCE.getMusic()
        return loadDataFromDB(Music::class.java)
    }


    override fun <T> loadDataFromDB(clz: Class<T>): T? {
        return super.loadDataFromDB(clz)
    }


    override fun <T : Any> insertDataToDB(t: T?) {
        super.insertDataToDB(t)
    }


}