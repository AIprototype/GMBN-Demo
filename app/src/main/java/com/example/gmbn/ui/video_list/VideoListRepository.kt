package com.example.gmbn.ui.video_list

import com.example.gmbn.data.network.ApiInterface
import com.example.gmbn.data.network.models.response.PlayListItemResponseModel
import retrofit2.Response
import javax.inject.Inject

class VideoListRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getPlayListItems(
        part: String,
        maxResults: Int,
        pageToken: String,
        playListId: String,
        apiKey: String
    ): Response<PlayListItemResponseModel> {
        return apiInterface.getSettingsData(part, maxResults, pageToken, playListId, apiKey)
    }
}