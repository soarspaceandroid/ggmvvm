package com.pajk.healthmanageapp.base.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.pajk.healthmanageapp.base.utils.PajkStatusBar

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 13:40
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.widget
 *----------------------------------------------------
 */
class StubStatusBar: LinearLayout{

    constructor(context: Context?) : this(context , null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs , -1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView()
    }

    private fun initView(){
        adjustHeight()
    }


    private fun adjustHeight(){
        layoutParams?.apply {
            height = PajkStatusBar.getStatusbarHeight(context)
            layoutParams = this
        }
    }




}