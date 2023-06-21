package com.example.tasks.project.di

import com.example.tasks.project.data.network.ProjectService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProjectModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): ProjectService {
        return builder
            .build()
            .create(ProjectService::class.java)
    }
}