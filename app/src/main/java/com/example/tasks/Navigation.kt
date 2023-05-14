package com.example.tasks

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasks.authentication.AuthenticationScreen
import com.example.tasks.authentication.AuthenticationViewModel


@Composable
fun Navigation(viewModel: AuthenticationViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.AuthenticationScreen.route) {
        composable(route = Screen.AuthenticationScreen.route) {

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult(),
                onResult = { result ->
                    run {

                        if (result.resultCode == Activity.RESULT_OK) {
                            Toast.makeText(context, "Result OK", Toast.LENGTH_SHORT).show()

                            viewModel.handleAuthorizationResponse(result.data!!)
                            Log.d("TasksLog", "result: ${result.resultCode} ")


                        } else
                            Toast.makeText(context, "Result Not OK", Toast.LENGTH_SHORT).show()

                    }
                }
            )
            AuthenticationScreen(navController) {
                val intent = viewModel.attemptAuthentication()
                launcher.launch(intent)
            }
        }
    }
}