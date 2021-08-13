package com.pajk.healthmanageapp.base.extend

import java.text.DecimalFormat

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 10:34
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.extend
 *----------------------------------------------------
 */

/**
 * float 保留两位小数
 */
fun Float.toPointTwo():String = DecimalFormat("0.00").format(this) //构造方法的字符格式这里如果小数不足2位,会以0补足.