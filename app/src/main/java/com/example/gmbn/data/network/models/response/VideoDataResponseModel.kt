package com.example.gmbn.data.network.models.response

import com.google.gson.annotations.SerializedName

data class VideoDataResponseModel(
    @SerializedName("etag") val etag: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("items") val videoItems: ArrayList<VideoItem>?
)

data class VideoItem(
    @SerializedName("contentDetails") val contentDetails: ContentDetails?
)

data class ContentDetails(
    @SerializedName("duration") var duration: String?
)