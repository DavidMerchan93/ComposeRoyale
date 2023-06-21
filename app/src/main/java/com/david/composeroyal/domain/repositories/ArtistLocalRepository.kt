package com.david.composeroyal.domain.repositories

interface ArtistLocalRepository {
    fun saveArtist()
    fun getAllArtist()
    fun getArtistById(id: String)
    fun deleteArtist(id: String)
}
