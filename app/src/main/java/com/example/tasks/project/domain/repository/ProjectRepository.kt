package com.example.tasks.project.domain.repository

import com.example.tasks.common.util.Result
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    suspend fun addProjectRemote(title: String): Flow<Result<Boolean>>
}