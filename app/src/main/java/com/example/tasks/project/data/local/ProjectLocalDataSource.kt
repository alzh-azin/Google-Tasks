package com.example.tasks.project.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectLocalDataSource @Inject constructor() {

    suspend fun insertProject(projectEntity: ProjectEntity) {}
}