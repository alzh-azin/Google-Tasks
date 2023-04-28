package com.example.google_tasks.ui.theme

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationState
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.google_tasks.Greeting
import com.example.google_tasks.R

@Composable
fun Authentication() {

    val lottieComposition: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.task))


    Column(modifier = Modifier.fillMaxSize()) {

        if (lottieComposition.isComplete) {

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_tasks),
                    contentDescription = "TODOLIST"
                )

                Text(
                    text = stringResource(id = R.string.label_todolist),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }



            LottieAnimation(
                composition = lottieComposition.value,
                iterations = LottieConstants.IterateForever
            )


            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                onClick = {}) {
                Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Authentication")
                    Text(text = "Continue with google" )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationPreview() {
    GoogleTasksTheme {
        Authentication()
    }
}