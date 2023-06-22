package com.david.composeroyal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.david.composeroyal.data.local.entities.ArtistEntity

@Dao
interface ArtistDao {
    @Query("SELECT * FROM ArtistEntity")
    fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM ArtistEntity WHERE artist_id=:id")
    fun getArtistById(id: String): ArtistEntity?

    @Insert
    fun saveArtist(artistEntity: ArtistEntity)

    @Query("DELETE FROM ArtistEntity WHERE artist_id=:id")
    fun deleteArtistById(id: String)
}
