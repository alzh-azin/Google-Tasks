package com.example.google_tasks.common.di

import com.example.google_tasks.common.data.preferences.GoogleTaskPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun providePreferences(preferences: GoogleTaskPreferences): Preferences
}