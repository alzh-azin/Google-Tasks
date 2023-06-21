package com.example.tasks.project.ui

import androidx.lifecycle.ViewModel
import com.example.tasks.project.domain.usercase.AddProject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val addProject: AddProject) : ViewModel() {


}