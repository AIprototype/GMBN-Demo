package com.example.gmbn.ui.video_list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gmbn.BuildConfig
import com.example.gmbn.data.network.ApiInterface
import com.example.gmbn.data.network.models.response.Item
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_ID = ""

class VideoListPagingSource constructor(
    private val apiInterface: ApiInterface
): PagingSource<String, Item>() {
    override fun getRefreshKey(state: PagingState<String, Item>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        val position = params.key?: STARTING_PAGE_ID

        return try {
            val data = apiInterface.getPlayListVideoData("snippet", BuildConfig.MAX_RESULTS, position, BuildConfig.PlaylistID, BuildConfig.API_KEY)
            LoadResult.Page(
                data = data.body()?.items as List<Item>,
                prevKey = data.body()?.prevPageToken,
                nextKey = data.body()?.nextPageToken
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}