package com.soar.vm.database

import androidx.room.Room
import com.soar.vm.base.extend.appContext
import com.soar.vm.model.Music

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/27
 *※ Time : 11:45
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.repository.room
 *----------------------------------------------------
 * 额外写的数据库扩展
 */
class DBManager {

    private var db :AppDatabase ? = null

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DBManager() }
    }

    constructor(){
        db =  Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "hmapp"
        ).build()

    }


    fun getMusic():Music{
        return db?.musicDao()?.getMusic()!!
    }

    /**
     * 获取music
     */
    fun getMusicDetail():Music.SongDetail{
        return db?.musicDao()?.getMusicDetail()!!
    }

}