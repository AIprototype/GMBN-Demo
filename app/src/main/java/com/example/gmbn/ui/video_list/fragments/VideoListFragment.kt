package com.example.gmbn.ui.video_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.gmbn.R
import com.example.gmbn.databinding.FragmentVideoListBinding
import com.example.gmbn.di.ViewModelFactory
import com.example.gmbn.ui.video_list.VideoDiffCallBack
import com.example.gmbn.ui.video_list.VideoListViewModel
import com.example.gmbn.utils.ItemClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_video_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoListFragment : Fragment(), ItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var videoListViewModel: VideoListViewModel
    private lateinit var binding: FragmentVideoListBinding
    private lateinit var videoListAdapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoListViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(VideoListViewModel::class.java)
        binding.viewModel = videoListViewModel
        setUi()
        setObservers()

        //Required to update with live data
        binding.lifecycleOwner = this
    }

    private fun setUi() {
        videoListAdapter = VideoListAdapter(VideoDiffCallBack(), this)
        video_list_rv.adapter = videoListAdapter

        videoListAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    main_progress_bar.visibility = View.VISIBLE
                    video_list_rv.visibility = View.GONE
                }
                is LoadState.NotLoading -> {
                    main_progress_bar.visibility = View.GONE
                    video_list_rv.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    main_progress_bar.visibility = View.GONE
                    video_list_rv.visibility = View.GONE
                }
            }
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
            videoListViewModel.apiData().collect {
                it.let {
                    videoListAdapter.submitData(it)
                }
            }
        }
    }

    override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
        videoListViewModel.updatePlayListItemList(videoListAdapter.snapshot().items as ArrayList)
        videoListViewModel.setPositionClicked(position)
    }
}