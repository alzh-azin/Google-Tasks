package com.example.tasks.project.data.network

import com.example.tasks.common.util.getResponseResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRemoteDataSource @Inject constructor(private val service: ProjectService) {

    suspend fun insertProject(projectRequestData: ProjectRequestData) =
        getResponseResult(
            { service.insertProject(projectRequestData) },
            "Error add project"
        )

}