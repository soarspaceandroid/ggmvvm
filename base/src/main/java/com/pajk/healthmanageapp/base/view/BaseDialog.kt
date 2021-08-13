package com.pajk.healthmanageapp.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.pajk.healthmanageapp.base.R
import com.pajk.healthmanageapp.base.extend.runThreadUI

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 10:16
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.view
 *----------------------------------------------------
 */
abstract class BaseDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.BaseDialog)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId() , container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    @LayoutRes
    abstract fun getLayoutId():Int

    abstract fun initView()

    override fun show(manager: FragmentManager, tag: String?) {
        runThreadUI {
            super.show(manager, tag)
        }
    }

    override fun dismiss() {
        if(!isVisible){
            return
        }
        runThreadUI {
            super.dismiss()
        }
    }

}