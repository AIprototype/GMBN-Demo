package com.example.gmbn.ui.video_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gmbn.data.network.models.response.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoListViewModel @Inject constructor(
    private val videoListRepository: VideoListRepository
) : ViewModel() {

    val playListItemLiveData = MutableLiveData<ArrayList<Item>>()
    val positionClicked = MutableLiveData<Int>()

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
}