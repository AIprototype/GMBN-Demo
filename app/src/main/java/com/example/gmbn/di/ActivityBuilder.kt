package com.example.gmbn.di

import com.example.gmbn.ui.video_list.VideoListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector()
    abstract fun bindVideoListActivity(): VideoListActivity
}