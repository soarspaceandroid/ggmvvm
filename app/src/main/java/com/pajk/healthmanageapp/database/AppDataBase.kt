package com.pajk.healthmanageapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pajk.healthmanageapp.model.Music
import com.pajk.healthmanageapp.repository.MusicDao

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/27
 *※ Time : 15:57
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.database
 *----------------------------------------------------
 */
@Database(entities = [Music::class ,Music.Songs::class ,Music.SongDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}