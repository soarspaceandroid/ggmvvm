package com.soar.vm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soar.vm.model.Music
import com.soar.vm.repository.MusicDao

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