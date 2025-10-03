package com.example.textres

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.textres.homeScreen.HomeScreen
import com.example.textres.homeScreen.Viewmodel
import com.example.textres.ui.theme.TextResTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewmodel by viewModel<Viewmodel>()
        setContent {
            TextResTheme {
                HomeScreen(viewmodel = viewmodel)
            }
        }
    }
}
