package com.pajk.healthmanageapp.base.extend

import android.view.View
import android.view.ViewGroup

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 9:19
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.extend
 *----------------------------------------------------
 */

/**
 * view gone
 */
fun View.gone(){
    visibility = View.GONE
}

/**
 * view visible
 */
fun View.visible(){
    visibility = View.VISIBLE
}

/**
 * view invisible
 */
fun View.invisible(){
    visibility = View.INVISIBLE
}


/**
 * 替换view , 如果要恢复, 调用方和传入放互换位置即可
 */
fun View.replaceView(replaceView:View){
    var layoutParam = layoutParams
    (parent as ViewGroup)?.let {
        val index = it.indexOfChild(this)
        it.removeView(this)
        it.addView(replaceView , index , layoutParam)
    }
}
