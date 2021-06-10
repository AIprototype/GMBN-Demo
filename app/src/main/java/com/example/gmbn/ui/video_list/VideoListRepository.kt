package com.example.gmbn.ui.video_list

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.gmbn.BuildConfig
import com.example.gmbn.data.network.ApiInterface
import com.example.gmbn.data.network.models.response.Item
import kotlinx.coroutines.flow.Flow
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
}