package com.padillahirpan.helloreminder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.padillahirpan.helloreminder.ui.theme.HelloReminderTheme
import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelloReminderTheme {
                SplashScreenView()
            }
        }
    }

    @Composable
    private fun SplashScreenView() {
        LaunchedEffect(key1 = true) {
            delay(2000)
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.reminder_logo),
                contentDescription = "splash_screen_logo"
            )
        }
    }
}