package com.example.learnkotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.learnkotlin.backend.database.blocked_app.BlockedAppViewModel
import com.example.learnkotlin.frontend.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[BlockedAppViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            MainScreen(viewModel)
        }
    }
}


