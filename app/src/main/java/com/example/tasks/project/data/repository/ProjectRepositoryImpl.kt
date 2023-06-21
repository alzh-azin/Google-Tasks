package com.example.tasks.project.data.repository

import com.example.tasks.common.util.Exceptions
import com.example.tasks.common.util.NetworkManagerMonitor
import com.example.tasks.common.util.Result
import com.example.tasks.project.data.local.ProjectLocalDataSource
import com.example.tasks.project.data.network.ProjectRemoteDataSource
import com.example.tasks.project.data.network.ProjectRequestData
import com.example.tasks.project.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    val projectRemoteDataSource: ProjectRemoteDataSource,
    val projectLocalDataSource: ProjectLocalDataSource,
    val networkManagerMonitor: NetworkManagerMonitor
) : ProjectRepository {


    override suspend fun addProjectRemote(title: String): Flow<Result<Boolean>> =
        flow {

            emit(Result.Loading(true))

            if (networkManagerMonitor.isCurrentlyConnected == true) {

                when (val result =
                    projectRemoteDataSource.insertProject(ProjectRequestData(title = title))) {
                    is Result.Success -> {
                        emit(Result.Success(true))
                    }

                    is Result.Error -> {
                        emit(Result.Error(result.exception))
                    }

                    else -> {}

                }

            } else {
                emit(Result.Error(exception = Exceptions.NetworkConnectionException))
            }
            emit(Result.Loading(false))

        }
}