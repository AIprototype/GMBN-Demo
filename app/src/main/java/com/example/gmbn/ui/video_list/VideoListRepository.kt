package com.example.gmbn.ui.video_list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gmbn.BuildConfig
import com.example.gmbn.data.network.ApiInterface
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.data.network.models.response.VideoDataResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class VideoListRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    fun getPlayListItemsPaging():Flow<PagingData<Item>> = Pager(
        config = PagingConfig(BuildConfig.MAX_RESULTS, 5, true),
        pagingSourceFactory = {
            VideoListPagingSource(apiInterface)
        }
    ).flow

    suspend fun getVideoDuration(videoId: String): Response<VideoDataResponseModel> {
        return apiInterface.getVideoDetails(videoId)
    }
}