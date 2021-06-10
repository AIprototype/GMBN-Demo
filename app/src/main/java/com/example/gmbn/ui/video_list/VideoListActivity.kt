package com.example.gmbn.ui.video_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gmbn.R
import com.example.gmbn.data.network.models.response.Item
import com.example.gmbn.databinding.ActivityVideoListBinding
import com.example.gmbn.di.ViewModelFactory
import com.example.gmbn.utils.ItemClickListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_video_list.*
import javax.inject.Inject

class VideoListActivity : AppCompatActivity(), ItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var videoListViewModel: VideoListViewModel
    private lateinit var binding: ActivityVideoListBinding
    private lateinit var videoListAdapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_list)
        //setContentView(R.layout.activity_video_list)
        AndroidInjection.inject(this)
        videoListViewModel =
            ViewModelProvider(this, viewModelFactory).get(VideoListViewModel::class.java)
        binding.viewModel = videoListViewModel

        setUi()
        setObservers()
    }

    private fun setUi() {
        videoListAdapter = VideoListAdapter(VideoDiffCallBack(), this)
        video_list_rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
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
                    Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setObservers() {
        videoListViewModel.list.observe(this, {
            if (it != null) {
                //videoListAdapter.submitList(it as List<Item?>?)
                videoListAdapter.submitData(lifecycle, it)
            }
        })
    }

    override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
        Toast.makeText(this, "clicked: $position", Toast.LENGTH_SHORT).show()
    }
}