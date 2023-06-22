package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.local.dao.ArtistDao
import com.david.composeroyal.data.local.entities.mapToDomain
import com.david.composeroyal.data.local.entities.mapToEntity
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistLocalRepository
import javax.inject.Inject

class ArtistLocalRepositoryImpl @Inject constructor(
    private val artistDao: ArtistDao,
) : ArtistLocalRepository {
    override fun saveArtist(artistModel: ArtistModel): Boolean {
        return try {
            artistDao.saveArtist(artistModel.mapToEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getAllArtist(): List<ArtistModel> {
        return artistDao.getAll().map {
            it.mapToDomain()
        }
    }

    override fun getArtistById(id: String): ArtistModel? {
        return artistDao.getArtistById(id)?.mapToDomain()
    }

    override fun deleteArtist(id: String): Boolean {
        return try {
            artistDao.deleteArtistById(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}
