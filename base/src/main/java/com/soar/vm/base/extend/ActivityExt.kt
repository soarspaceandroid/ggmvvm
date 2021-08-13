package com.soar.vm.base.extend

import androidx.appcompat.app.AppCompatActivity
import com.soar.vm.base.view.LoadingDialog

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 14:35
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.extend
 *----------------------------------------------------
 */

private val loadingDialog:LoadingDialog = LoadingDialog()

/**
 * 显示loading
 */
fun AppCompatActivity.showLoading(){
    supportFragmentManager.executePendingTransactions()
    if(!loadingDialog.isAdded) {
        loadingDialog.show(supportFragmentManager, "loading")
    }
}

/**
 * 隐藏loading ,如果是请求请注意hide的时机,可能多个请求并发
 */
fun AppCompatActivity.hideLoading(){
    loadingDialog?.dismiss()
}