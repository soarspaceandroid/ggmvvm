package com.pajk.healthmanageapp.base.extend

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 10:35
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.extend
 *----------------------------------------------------
 */

/**
 * String 类型验证是否是电话号码
 */
fun String.isPhone():Boolean =  Pattern.compile("^[1]\\d{10}$").matcher(this).matches()

/**
 * 时间戳format 日期格式
 * @formatStr YYYY-MM-dd HH:mm
 */
fun String.formatData(formatStr:String):String = SimpleDateFormat(formatStr).format(Date(this.toLong()))
