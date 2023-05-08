package com.example.tasks.common.di

import com.example.tasks.common.data.preferences.Preferences
import com.example.tasks.common.data.preferences.TasksPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class PreferencesModule {

    @Binds
    abstract fun providePreferences(preferences: TasksPreferences): Preferences

}