package com.example.gmbn.data.network.models.response

import android.graphics.Movie
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class PlayListItemResponseModel(
    @SerializedName("etag") val etag: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("nextPageToken") val nextPageToken: String?,
    @SerializedName("prevPageToken") val prevPageToken: String?,
    @SerializedName("pageInfo") val pageInfo: PageInfo?,
    @SerializedName("items") val items: ArrayList<Item>?


) {
    override fun toString(): String {
        return "PlayListItemResponseModel(etag=$etag, kind=$kind, nextPageToken=$nextPageToken, prevPageToken=$prevPageToken, pageInfo=$pageInfo, items=$items)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayListItemResponseModel

        if (etag != other.etag) return false
        if (kind != other.kind) return false
        if (nextPageToken != other.nextPageToken) return false
        if (prevPageToken != other.prevPageToken) return false
        if (pageInfo != other.pageInfo) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int {
        var result = etag?.hashCode() ?: 0
        result = 31 * result + (kind?.hashCode() ?: 0)
        result = 31 * result + (nextPageToken?.hashCode() ?: 0)
        result = 31 * result + (prevPageToken?.hashCode() ?: 0)
        result = 31 * result + (pageInfo?.hashCode() ?: 0)
        result = 31 * result + (items?.hashCode() ?: 0)
        return result
    }


}

data class PageInfo(
    @SerializedName("resultsPerPage") val resultsPerPage: Int?,
    @SerializedName("totalResults") val totalResults: Int?,


    ) {
    override fun toString(): String {
        return "PageInfo(resultsPerPage=$resultsPerPage, totalResults=$totalResults)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PageInfo

        if (resultsPerPage != other.resultsPerPage) return false
        if (totalResults != other.totalResults) return false

        return true
    }

    override fun hashCode(): Int {
        var result = resultsPerPage ?: 0
        result = 31 * result + (totalResults ?: 0)
        return result
    }


}

data class Item(
    @SerializedName("etag") val etag: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("snippet") val snippet: Snippet?,
) {
    override fun toString(): String {
        return "Item(etag=$etag, id=$id, kind=$kind, snippet=$snippet)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (etag != other.etag) return false
        if (id != other.id) return false
        if (kind != other.kind) return false
        if (snippet != other.snippet) return false

        return true
    }

    override fun hashCode(): Int {
        var result = etag?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (kind?.hashCode() ?: 0)
        result = 31 * result + (snippet?.hashCode() ?: 0)
        return result
    }
}

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
) {
    override fun toString(): String {
        return "Snippet(channelId=$channelId, channelTitle=$channelTitle, description=$description, playlistId=$playlistId, position=$position, publishedAt=$publishedAt, title=$title, videoOwnerChannelId=$videoOwnerChannelId, videoOwnerChannelTitle=$videoOwnerChannelTitle, thumbnails=$thumbnails, resourceId=$resourceId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Snippet

        if (channelId != other.channelId) return false
        if (channelTitle != other.channelTitle) return false
        if (description != other.description) return false
        if (playlistId != other.playlistId) return false
        if (position != other.position) return false
        if (publishedAt != other.publishedAt) return false
        if (title != other.title) return false
        if (videoOwnerChannelId != other.videoOwnerChannelId) return false
        if (videoOwnerChannelTitle != other.videoOwnerChannelTitle) return false
        if (thumbnails != other.thumbnails) return false
        if (resourceId != other.resourceId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channelId?.hashCode() ?: 0
        result = 31 * result + (channelTitle?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (playlistId?.hashCode() ?: 0)
        result = 31 * result + (position ?: 0)
        result = 31 * result + (publishedAt?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (videoOwnerChannelId?.hashCode() ?: 0)
        result = 31 * result + (videoOwnerChannelTitle?.hashCode() ?: 0)
        result = 31 * result + (thumbnails?.hashCode() ?: 0)
        result = 31 * result + (resourceId?.hashCode() ?: 0)
        return result
    }


}

data class Thumbnails(
    @SerializedName("medium") val medium: Medium?
) {
    override fun toString(): String {
        return "Thumbnails(medium=$medium)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Thumbnails

        if (medium != other.medium) return false

        return true
    }

    override fun hashCode(): Int {
        return medium?.hashCode() ?: 0
    }


}

data class Medium(
    @SerializedName("height") val height: Int?,
    @SerializedName("width") val width: Int?,
    @SerializedName("url") val url: String?
) {
    override fun toString(): String {
        return "Medium(height=$height, width=$width, url=$url)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Medium

        if (height != other.height) return false
        if (width != other.width) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height ?: 0
        result = 31 * result + (width ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }


}

data class ResourceID(
    @SerializedName("kind") val kind: String?,
    @SerializedName("videoId") val videoId: String?
) {
    override fun toString(): String {
        return "ResourceID(kind=$kind, videoId=$videoId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceID

        if (kind != other.kind) return false
        if (videoId != other.videoId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = kind?.hashCode() ?: 0
        result = 31 * result + (videoId?.hashCode() ?: 0)
        return result
    }

}