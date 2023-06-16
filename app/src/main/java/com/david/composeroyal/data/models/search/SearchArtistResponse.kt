package com.david.composeroyal.data.models.search

import com.david.composeroyal.data.models.common.Image
import com.david.composeroyal.domain.models.ArtistModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchArtistResponse(
    val artists: Artists,
)

@Serializable
data class Artists(
    val items: List<Item>,
    val href: String,
    val limit: Int,
    val total: Int,
)

@Serializable
data class Item(
    val id: String,
    val name: String,
    val images: List<Image>,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val popularity: Int,
    val type: String,
    val uri: String,
)

@Serializable
data class ExternalUrls(
    val spotify: String,
)

@Serializable
data class Followers(
    val href: String? = String(),
    val total: Int,
)

fun SearchArtistResponse.mapToDomain(): List<ArtistModel> {
    return artists.items.map { artist ->
        ArtistModel(
            id = artist.id,
            name = artist.name,
            images = artist.images.map { it.url },
            externalUrl = artist.externalUrls.spotify,
            followersCount = artist.followers.total,
            genres = artist.genres.take(4).filter { it.isNotEmpty() },
            popularity = artist.popularity,
        )
    }
}
