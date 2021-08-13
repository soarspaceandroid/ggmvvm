package com.pajk.healthmanageapp.repository

import androidx.room.Dao
import androidx.room.Query
import com.pajk.healthmanageapp.model.Music

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/27
 *※ Time : 14:01
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.repository
 *----------------------------------------------------
 */
@Dao
interface MusicDao {

    @Query("SELECT * FROM music")
    fun getMusic():Music

    @Query("SELECT * From songs")
    fun getMusicSong():Music.Songs

    @Query("SELECT * From song_detail")
    fun getMusicDetail():Music.SongDetail
}