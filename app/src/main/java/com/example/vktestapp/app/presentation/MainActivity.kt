package com.example.vktestapp.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vktestapp.app.viewmodel.MainViewModel
import com.example.vktestapp.core.ui.theme.VkTestAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModel()

        setContent {
            KoinContext {
                VkTestAppTheme {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}
