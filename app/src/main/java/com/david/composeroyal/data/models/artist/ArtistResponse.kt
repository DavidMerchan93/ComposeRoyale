package com.david.composeroyal.data.models.artist

import com.david.composeroyal.data.models.common.ExternalUrls
import com.david.composeroyal.data.models.common.Followers
import com.david.composeroyal.data.models.common.Image
import com.david.composeroyal.data.models.common.mapToDomain
import com.david.composeroyal.domain.models.ArtistModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponse(
    val id: String,
    val name: String,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers? = null,
    val genres: List<String>? = emptyList(),
    val href: String,
    val images: List<Image>? = emptyList(),
    val popularity: Int? = 0,
    val type: String,
    val uri: String,
)

fun ArtistResponse.mapToDomain(): ArtistModel {
    return ArtistModel(
        id = id,
        name = name,
        images = images?.mapToDomain() ?: emptyList(),
        externalUrl = externalUrls.spotify,
        followersCount = followers?.total ?: 0,
        genres = genres ?: emptyList(),
        popularity = popularity ?: 0,
    )
}
