package com.example.gmbn.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gmbn.di.ViewModelFactory
import com.example.gmbn.di.ViewModelKey
import com.example.gmbn.ui.video_list.VideoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VideoListViewModel::class)
    internal abstract fun bindVideoListViewModel(videoListViewModel: VideoListViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}