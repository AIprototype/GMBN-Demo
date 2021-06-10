package com.example.gmbn.ui.video_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gmbn.R
import com.example.gmbn.databinding.FragmentVideoDetailBinding
import com.example.gmbn.di.ViewModelFactory
import com.example.gmbn.ui.video_list.VideoListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class VideoDetailFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var videoListViewModel: VideoListViewModel
    private lateinit var binding: FragmentVideoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoListViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(VideoListViewModel::class.java)

        setUi()

        //Required to update with live data
        binding.lifecycleOwner = this
    }

    private fun setUi() {
        val itemDataList = videoListViewModel.playListItemLiveData.value
        val position = videoListViewModel.positionClicked.value
        if(itemDataList != null && position != null) {
            binding.itemData = itemDataList[position]
        }
    }
}