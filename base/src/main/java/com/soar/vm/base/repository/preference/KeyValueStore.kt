package com.soar.vm.base.repository.preference

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/27
 *※ Time : 15:57
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.database
 *----------------------------------------------------
 */
@Database(entities = [StoreBean::class], version = 1)
abstract class KeyValueStore : RoomDatabase() {
    abstract fun keyValueStoreDao(): KeyValueStoreDao
}