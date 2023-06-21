package com.example.tasks.project.domain.usercase

import com.example.tasks.project.domain.repository.ProjectRepository
import javax.inject.Inject

class AddProject @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend fun invoke(title: String) = projectRepository.addProjectRemote(title)
}