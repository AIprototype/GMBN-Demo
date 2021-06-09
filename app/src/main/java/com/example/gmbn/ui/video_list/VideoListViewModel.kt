package com.example.gmbn.ui.video_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gmbn.BuildConfig
import com.example.gmbn.GmbnApp
import com.example.gmbn.R
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.data.network.models.response.PlayListItemResponseModel
import com.example.gmbn.utils.NetWorkUtil
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class VideoListViewModel @Inject constructor(
    private val context: Application,
    private val videoListRepository: VideoListRepository
) : ViewModel() {

    private val playListItemLiveData = MutableLiveData<ArrayList<Item>>()
    private val playListResponseLiveData = MutableLiveData<PlayListItemResponseModel>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isNetworkAvailableLiveData = MutableLiveData<Boolean>()
    private val errorMessageLiveData = MutableLiveData<String>()

    fun loadingListener():MutableLiveData<Boolean> {
        return isLoadingLiveData
    }

    fun networkAvailabilityListener(): MutableLiveData<Boolean> {
        return isNetworkAvailableLiveData
    }

    fun errorMessageListener(): MutableLiveData<String> {
        return errorMessageLiveData
    }

    fun playListResponseListener(): MutableLiveData<PlayListItemResponseModel> {
        return playListResponseLiveData
    }

    fun playListItemListener(): MutableLiveData<ArrayList<Item>> {
        return playListItemLiveData
    }

    fun fetchVideoData(nextPageToken: String) {
        isLoadingLiveData.value = true
        if (NetWorkUtil.isNetworkAvailable(context)) {
            isNetworkAvailableLiveData.value = true
            viewModelScope.launch {
                try {
                    val data = videoListRepository.getPlayListItems(
                        "snippet",
                        BuildConfig.MAX_RESULTS,
                        nextPageToken,
                        BuildConfig.PlaylistID,
                        BuildConfig.API_KEY)

                    playListResponseLiveData.value = data.body()
                    playListItemLiveData.value = data.body()?.items
                    isLoadingLiveData.value = false
                    
                } catch (e: Exception) {
                    isLoadingLiveData.value = false
                    errorMessageLiveData.value = e.message
                }
            }
        } else {
            isNetworkAvailableLiveData.value = false
            errorMessageLiveData.value = "No network !"
        }
    }
}