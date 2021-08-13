package com.pajk.healthmanageapp.base.repository.preference

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/28
 *※ Time : 9:14
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.repository.preference
 *----------------------------------------------------
 */
@Entity(tableName = "keyvalue")
class StoreBean(@PrimaryKey var key:String, var value:String)