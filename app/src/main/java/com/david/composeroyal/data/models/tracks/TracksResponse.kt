package com.david.composeroyal.data.models.tracks

import com.david.composeroyal.data.models.album.AlbumResponse
import com.david.composeroyal.data.models.album.mapToDomain
import com.david.composeroyal.data.models.artist.ArtistResponse
import com.david.composeroyal.data.models.artist.mapToDomain
import com.david.composeroyal.data.models.common.ExternalUrls
import com.david.composeroyal.data.models.common.mapToDomain
import com.david.composeroyal.domain.models.TrackModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TracksResponse(
    val tracks: List<Track>,
)

@Serializable
data class Track(
    val id: String,
    val name: String,
    val album: AlbumResponse,
    val artists: List<ArtistResponse>,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = emptyList(),
    @SerialName("disc_number")
    val discNumber: Int,
    @SerialName("duration_ms")
    val durationMs: Int,
    val explicit: Boolean,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    @SerialName("is_local")
    val isLocal: Boolean,
    @SerialName("is_playable")
    val isPlayable: Boolean,
    val popularity: Int,
    @SerialName("preview_url")
    val previewUrl: String,
    val restrictions: Restrictions? = null,
    @SerialName("track_number")
    val trackNumber: Int,
    val type: String,
    val uri: String,
)

@Serializable
data class Restrictions(
    val reason: String,
)

fun Track.mapToDomain(): TrackModel {
    return TrackModel(
        id = id,
        name = name,
        popularity = popularity,
        uri = externalUrls.spotify,
        album = album.mapToDomain(),
        artist = artists.firstOrNull()?.mapToDomain(),
    )
}
