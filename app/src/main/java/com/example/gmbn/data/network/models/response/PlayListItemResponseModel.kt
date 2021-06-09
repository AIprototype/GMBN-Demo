package com.example.gmbn.data.network.models.response

import com.google.gson.annotations.SerializedName

data class PlayListItemResponseModel (
    @SerializedName("etag") val etag: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfo?,
    @SerializedName("items") val items: ArrayList<Item>?
)

data class PageInfo(
    @SerializedName("resultsPerPage") val resultsPerPage: Int?,
    @SerializedName("totalResults") val totalResults: Int?,
)

data class Item(
    @SerializedName("etag") val etag: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("snippet") val snippet: Snippet?,
)

data class Snippet(
    @SerializedName("channelId") val channelId: String?,
    @SerializedName("channelTitle") val channelTitle: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("playlistId") val playlistId: String?,
    @SerializedName("position") val position: Int?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("videoOwnerChannelId") val videoOwnerChannelId: String?,
    @SerializedName("videoOwnerChannelTitle") val videoOwnerChannelTitle: String?,
    @SerializedName("thumbnails") val thumbnails: Thumbnails?,
    @SerializedName("resourceId") val resourceId: ResourceID?,
)

data class Thumbnails(
    @SerializedName("medium") val medium: Medium?
)

data class Medium(
    @SerializedName("height") val height: Int?,
    @SerializedName("width") val width: Int?,
    @SerializedName("url") val url: String?
)

data class ResourceID(
    @SerializedName("kind") val kind: String?,
    @SerializedName("videoId") val videoId: String?
)