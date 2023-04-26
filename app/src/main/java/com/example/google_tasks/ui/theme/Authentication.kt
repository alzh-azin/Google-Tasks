package com.example.google_tasks.ui.theme

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationState
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.google_tasks.R

@Composable
fun Authentication() {

    val lottieComposition: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.task))


    Column(modifier = Modifier.fillMaxSize()) {

        if (lottieComposition.isComplete) {
            LottieAnimation(
                composition = lottieComposition.value,
                iterations = LottieConstants.IterateForever
            )


            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Authentication")
                    Text(text = "Continue with google")
                }
            }
        }


    }
}