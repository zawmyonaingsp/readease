package com.sevenpeaks.zawmyonaing.readease.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.domain.repository.PreferenceRepository
import com.sevenpeaks.zawmyonaing.readease.navigation.ArticleListDestination
import com.sevenpeaks.zawmyonaing.readease.navigation.Destination
import com.sevenpeaks.zawmyonaing.readease.navigation.OnboardingDestination
import com.sevenpeaks.zawmyonaing.readease.navigation.graphs.RootNavGraph
import com.sevenpeaks.zawmyonaing.readease.ui.theme.ReadEaseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var lastBackPressTime = 0L

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { handleBackPress() }

        setContent {
            ReadEaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var entryDestination by remember { mutableStateOf<Destination?>(null) }

                    LaunchedEffect(Unit) {
                        val user = preferenceRepository.getUser()
                        entryDestination =
                            if (user != null) ArticleListDestination else OnboardingDestination
                    }

                    entryDestination?.let {
                        RootNavGraph(
                            navHostController = rememberNavController(),
                            startDestination = it
                        )
                    }

                }
            }
        }
    }

    private fun handleBackPress() {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastBackPressTime) < BACK_PRESS_TIME_GAP_MILLIS) {
            finish()
        } else {
            lastBackPressTime = currentTime
            Toast.makeText(this, R.string.msg_double_back_to_exit, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val BACK_PRESS_TIME_GAP_MILLIS = 2000
    }
}