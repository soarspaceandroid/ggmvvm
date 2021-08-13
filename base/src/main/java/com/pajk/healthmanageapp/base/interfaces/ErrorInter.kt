package com.pajk.healthmanageapp.base.interfaces

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.pajk.healthmanageapp.base.R
import kotlinx.android.synthetic.main.layout_error.view.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/29
 *※ Time : 9:24
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.interfaces
 *----------------------------------------------------
 */
interface ErrorInter {

    /**
     * get error view ,传入点击后需要做的操作
     */
    fun getErrorView(context: Context, msg:String, redoAction:(View)->Unit): View {
        return LayoutInflater.from(context).inflate(R.layout.layout_error , null).apply {
            redo.text = "$msg 点击重试"
            redo.setOnClickListener{
                redoAction(this)
            }
        }
    }
}