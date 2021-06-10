package com.example.gmbn.di.module

import com.example.gmbn.ui.video_list.fragments.VideoDetailFragment
import com.example.gmbn.ui.video_list.fragments.VideoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class VideoListModule {
    @ContributesAndroidInjector
    internal abstract fun contributeVideoListFragment(): VideoListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeVideoDetailFragment(): VideoDetailFragment
}