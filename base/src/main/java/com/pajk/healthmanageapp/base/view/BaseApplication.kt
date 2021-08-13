package com.pajk.healthmanageapp.base.view

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/21
 *※ Time : 13:51
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.view
 *----------------------------------------------------
 */
abstract class BaseApplication :Application(), ViewModelStoreOwner {

    companion object{
        lateinit var application: BaseApplication
    }

    private var mFactory: ViewModelProvider.Factory? = null
    private lateinit var mAppViewModelStore: ViewModelStore

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        mAppViewModelStore = ViewModelStore()
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }


    abstract fun getDataBaseName():String


}