package com.example.tasks.project.data.network

import com.example.tasks.project.domain.model.Project
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectResponseData(
    val id: String? = null,
    val title: String? = null,
    val kind: String? = null,
    val etag: String? = null,
    val updated: String? = null,
    val selfLink: String? = null,
)

fun ProjectResponseData.toDomainModel() = Project(
    id = id,
    title = title

)