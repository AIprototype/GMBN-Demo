package com.example.gmbn.ui.video_list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.gmbn.R
import com.example.gmbn.databinding.ActivityVideoListBinding
import com.example.gmbn.di.ViewModelFactory
import com.example.gmbn.ui.video_list.fragments.VideoDetailFragment
import com.example.gmbn.ui.video_list.fragments.VideoListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class VideoListActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var videoListViewModel: VideoListViewModel
    private lateinit var binding: ActivityVideoListBinding

    private lateinit var videoListFragment: VideoListFragment
    private lateinit var videoDetailFragment: VideoDetailFragment

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = fragmentDispatchingAndroidInjector

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

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            addToBackStack(fragment::class.java.simpleName)
            commit()
        }
    }

    private fun setUi() {
        videoListFragment = VideoListFragment()
        videoDetailFragment = VideoDetailFragment()

        makeCurrentFragment(videoListFragment)
    }

    private fun setObservers() {
        videoListViewModel.positionClickedListener().observe(this, {
            makeCurrentFragment(videoDetailFragment)
        })
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun clearBackStack() {
        val fm: FragmentManager = supportFragmentManager
        val count: Int = fm.backStackEntryCount
        for (i in 0 until count) {
            fm.popBackStackImmediate()
        }
    }
}