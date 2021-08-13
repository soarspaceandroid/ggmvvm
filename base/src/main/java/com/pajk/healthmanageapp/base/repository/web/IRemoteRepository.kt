package com.pajk.healthmanageapp.base.repository.web

import androidx.lifecycle.MutableLiveData

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/26
 *※ Time : 18:06
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.model
 *----------------------------------------------------
 */
interface IRemoteRepository{


    /**
     * Post Request
     */
    fun <T> postRequest(showLoadig:Boolean ,clz:Class<T> , params :Map<String , String>)


}