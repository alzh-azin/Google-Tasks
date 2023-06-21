package com.example.tasks.common.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
    val isCurrentlyConnected: Boolean?
}