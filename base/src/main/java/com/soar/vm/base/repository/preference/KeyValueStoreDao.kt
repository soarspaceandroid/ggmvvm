package com.soar.vm.base.repository.preference

import androidx.room.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/28
 *※ Time : 9:16
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.repository.preference
 *----------------------------------------------------
 */
@Dao
interface KeyValueStoreDao {

    @Query("UPDATE keyvalue SET value = :value WHERE `key` = :key")
    fun update(key:String , value:String)

    @Query("INSERT INTO keyvalue values (:key , :value)")
    fun put(key:String , value:String)

    @Query("SELECT value FROM keyvalue WHERE `key` = :key")
    fun get(key:String):String

    @Query("DELETE FROM keyvalue WHERE `key` = :key")
    fun delete(key:String)


}