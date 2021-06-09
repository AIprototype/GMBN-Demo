package com.example.gmbn.ui.video_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gmbn.R
import com.example.gmbn.databinding.ActivityVideoListBinding
import com.example.gmbn.di.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class VideoListActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var videoListViewModel: VideoListViewModel
    private lateinit var binding: ActivityVideoListBinding

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

        videoListViewModel.fetchVideoData("")
    }

    private fun setObservers() {
        videoListViewModel.loadingListener().observe(this, {
            if(it) {
                Toast.makeText(this,"Loading..", Toast.LENGTH_SHORT).show()
            }
        })
        videoListViewModel.errorMessageListener().observe(this, {
            if(it != null) {
                Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUi() {

    }
}