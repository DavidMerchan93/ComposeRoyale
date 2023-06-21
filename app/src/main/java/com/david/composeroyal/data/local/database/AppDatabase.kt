package com.david.composeroyal.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.composeroyal.data.local.dao.ArtistDao
import com.david.composeroyal.data.local.entities.ArtistEntity

@Database(entities = [ArtistEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}
