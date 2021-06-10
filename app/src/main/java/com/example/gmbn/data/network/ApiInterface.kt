package com.example.gmbn.data.network

import com.example.gmbn.BuildConfig
import com.example.gmbn.data.network.models.response.PlayListItemResponseModel
import com.example.gmbn.data.network.models.response.VideoDataResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // https://youtube.googleapis.com/youtube/v3/playlistItems
// ?part=snippet
// &maxResults=3
// &pageToken=CDIQAA
// &playlistId=UU_A--fhX5gea0i4UtpD99Gg
// &key=AIzaSyD7M4VLaasbIUuT6e9Jc9YKt29TKiLeFKE
    @GET("youtube/v3/playlistItems")
    suspend fun getPlayListVideoData(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String
    ): Response<PlayListItemResponseModel>

    @GET("youtube/v3/videos")
    suspend fun getVideoDetails(
        @Query("id") videoId: String,
        @Query("part") part: String = "contentDetails",
        @Query("key") key: String = BuildConfig.API_KEY,
    ): Response<VideoDataResponseModel>

}