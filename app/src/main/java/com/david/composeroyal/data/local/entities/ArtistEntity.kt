package com.david.composeroyal.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.david.composeroyal.domain.models.ArtistModel

@Entity
data class ArtistEntity(
    @PrimaryKey
    val uid: Int = 0,
    @ColumnInfo(name = "artist_id")
    val artistId: String,
    val name: String,
    val image: String,
    val followers: Int,
    val popularity: Int,
    val genders: String,
)

fun ArtistModel.mapToEntity(): ArtistEntity {
    return ArtistEntity(
        artistId = id,
        name = name,
        followers = followersCount,
        popularity = popularity,
        image = images.randomOrNull() ?: String(),
        genders = genres.joinToString(","),
    )
}

fun ArtistEntity.mapToDomain(): ArtistModel {
    return ArtistModel(
        id = artistId,
        name = name,
        images = listOf(image),
        externalUrl = String(),
        followersCount = followers,
        genres = genders.split(","),
        popularity = popularity,
    )
}
