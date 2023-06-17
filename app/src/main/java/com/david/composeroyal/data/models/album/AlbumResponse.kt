package com.david.composeroyal.data.models.album

import com.david.composeroyal.data.models.artist.ArtistResponse
import com.david.composeroyal.data.models.common.ExternalUrls
import com.david.composeroyal.data.models.common.Image
import com.david.composeroyal.data.models.common.mapToDomain
import com.david.composeroyal.domain.models.AlbumModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponse(
    val id: String,
    val name: String,
    val artists: List<ArtistResponse>,
    val images: List<Image>,
    @SerialName("album_type")
    val albumType: String,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    @SerialName("is_playable")
    val isPlayable: Boolean,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("total_tracks")
    val totalTracks: Int,
    val type: String,
    val uri: String,
)

fun AlbumResponse.mapToDomain(): AlbumModel {
    return AlbumModel(
        id = id,
        name = name,
        albumType = albumType,
        images = images.mapToDomain(),
        releasedDate = releaseDate,
        type = type,
        uri = uri,
    )
}
