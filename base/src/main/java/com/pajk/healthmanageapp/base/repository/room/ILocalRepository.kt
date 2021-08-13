package com.pajk.healthmanageapp.base.repository.room

import androidx.lifecycle.MutableLiveData

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/26
 *※ Time : 18:06
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.model
 *----------------------------------------------------
 * app类继承实现
 */
interface ILocalRepository{

    /**
     * load data from db
     */
    fun <T> loadDataFromDB(clz:Class<T>): T?

    /**
     * insert data
     */
    fun <T:Any> insertDataToDB(t:T?)


    /**
     * del data from db
     */
    fun <T:Any> delDataFromDB(t:T)

}