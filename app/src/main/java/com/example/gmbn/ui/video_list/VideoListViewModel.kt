package com.example.gmbn.ui.video_list

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gmbn.data.network.models.response.ContentDetails
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.utils.NetWorkUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class VideoListViewModel @Inject constructor(
    private val context: Application,
    private val videoListRepository: VideoListRepository
) : ViewModel() {

    val playListItemLiveData = MutableLiveData<ArrayList<Item>>()
    val positionClicked = MutableLiveData<Int>()
    private val videoContentDetails = MutableLiveData<ContentDetails>()
    private val isLoading = MutableLiveData<Boolean>()
    private val serverMessage = MutableLiveData<String>()

    fun apiData(): Flow<PagingData<Item>> {
        return videoListRepository.getPlayListItemsPaging().cachedIn(viewModelScope)
    }

    fun updatePlayListItemList(items: ArrayList<Item>) {
        playListItemLiveData.value = items
    }

    fun playListItemListener(): MutableLiveData<ArrayList<Item>> {
        return playListItemLiveData
    }

    fun setPositionClicked(pos: Int) {
        positionClicked.value = pos
    }

    fun positionClickedListener(): MutableLiveData<Int> {
        return positionClicked
    }

    fun fetchVideoDuration(videoId: String) {
        isLoading.value = true
        if (NetWorkUtil.isNetworkAvailable(context)) {
            viewModelScope.launch {
                try {
                    val data = videoListRepository.getVideoDuration(videoId)
                    isLoading.value = false
                    if (data.body()?.videoItems?.size ?: 0 > 0) {
                        val duration = data.body()!!.videoItems!![0].contentDetails?.duration

                        if (duration != null) {
                            data.body()!!.videoItems!![0].contentDetails?.duration = parseDuration(duration)
                        }

                        videoContentDetails.value = data.body()!!.videoItems!![0].contentDetails
                    }

                } catch (e: Exception) {
                    serverMessage.value = e.message
                    isLoading.value = false
                }
            }
        } else {
            isLoading.value = false
            serverMessage.value = "No network connection !"
        }
    }

    private fun parseDuration(duration: String): String? {
        var duration = duration
        duration = if (duration.contains("PT")) duration.replace("PT", "") else duration
        duration = if (duration.contains("S")) duration.replace("S", "") else duration
        duration = if (duration.contains("H")) duration.replace("H", ":") else duration
        duration = if (duration.contains("M")) duration.replace("M", ":") else duration
        val split = duration.split(":").toTypedArray()
        for (i in split.indices) {
            val item = split[i]
            split[i] = if (item.length <= 1) "0$item" else item
        }
        return TextUtils.join(":", split)
    }

    fun videoContentDetailsListener(): MutableLiveData<ContentDetails> {
        return videoContentDetails
    }

    fun isLoadingListener(): MutableLiveData<Boolean> {
        return isLoading
    }

    fun serverMessageListener(): MutableLiveData<String> {
        return serverMessage
    }
}