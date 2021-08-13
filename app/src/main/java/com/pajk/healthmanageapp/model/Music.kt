package com.pajk.healthmanageapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/21
 *※ Time : 16:19
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp
 *----------------------------------------------------
 */

@Entity(tableName = "music")
class Music{
    var result: Songs? = null
    @Entity(tableName = "songs")
    data class Songs(var songs:List<SongDetail>){
        override fun toString(): String {
            return "Songs(songs=$songs)"
        }
    }
    @Entity(tableName = "song_detail")
    data class SongDetail(@PrimaryKey var id:String, var name:String){
        override fun toString(): String {
            return "SongDetail(id='$id', name='$name')"
        }
    }

    override fun toString(): String {
        return "Music(result=$result)"
    }


}
