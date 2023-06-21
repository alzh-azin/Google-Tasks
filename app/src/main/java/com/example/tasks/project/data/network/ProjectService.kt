package com.example.tasks.project.data.network

import com.example.tasks.common.data.network.ApiHelper.BASE_ENDPOINT
import com.example.tasks.common.data.network.ApiHelper.PROJECT_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectService {

    @POST(BASE_ENDPOINT + PROJECT_ENDPOINT)
    suspend fun insertProject(
        @Body body: ProjectRequestData
    ): Response<ProjectResponseData>
}