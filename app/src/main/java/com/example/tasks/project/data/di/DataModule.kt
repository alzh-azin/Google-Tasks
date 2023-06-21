package com.example.tasks.project.data.di

import com.example.tasks.project.data.repository.ProjectRepositoryImpl
import com.example.tasks.project.domain.repository.ProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindProjectRepository(
        repository: ProjectRepositoryImpl
    ): ProjectRepository
}