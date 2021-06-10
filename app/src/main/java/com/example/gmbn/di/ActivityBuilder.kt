package com.example.gmbn.di

import com.example.gmbn.di.module.VideoListModule
import com.example.gmbn.ui.video_list.VideoListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(VideoListModule::class)])
    abstract fun bindVideoListActivity(): VideoListActivity
}