package com.david.composeroyal.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtistEntity(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "artist_id")
    val artistId: String,
    val name: String,
    val image: String,
    val followers: Int,
    val popularity: Int,
)
