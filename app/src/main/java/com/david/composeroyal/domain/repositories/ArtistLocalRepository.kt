package com.david.composeroyal.domain.repositories

import com.david.composeroyal.domain.models.ArtistModel

interface ArtistLocalRepository {
    fun saveArtist(
        artistId: String,
        name: String,
        image: String,
        followers: Int,
        popularity: Int,
        genders: List<String>,
    ): Boolean

    fun getAllArtist(): List<ArtistModel>
    fun getArtistById(id: String): ArtistModel?
    fun deleteArtist(id: String): Boolean
}
