package com.example.gmbn.di

import android.app.Application
import com.example.gmbn.GmbnApp
import com.example.gmbn.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@SuppressWarnings("unchecked", "unsafe")
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GmbnApp)
}