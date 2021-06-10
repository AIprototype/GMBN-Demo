package com.example.gmbn.ui.video_list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.gmbn.BuildConfig
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.data.network.models.response.PlayListItemResponseModel
import com.example.gmbn.utils.NetWorkUtil
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class VideoListViewModel @Inject constructor(
    private val context: Application,
    private val videoListRepository: VideoListRepository
) : ViewModel() {

    private val playListItemLiveData = MutableLiveData<ArrayList<Item>>()
    private val playListResponseLiveData = MutableLiveData<PlayListItemResponseModel>()

    val list:LiveData<PagingData<Item>> = videoListRepository.getPlayListItemsPaging()

    private var nextPageId: String? = ""

    fun playListResponseListener(): MutableLiveData<PlayListItemResponseModel> {
        return playListResponseLiveData
    }

    fun playListItemListener(): MutableLiveData<ArrayList<Item>> {
        return playListItemLiveData
    }
}