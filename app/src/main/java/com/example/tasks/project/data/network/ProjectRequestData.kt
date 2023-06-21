package com.example.tasks.project.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectRequestData(
    val title: String? = null

)