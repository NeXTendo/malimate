package com.techohns.moneymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.techohns.moneymate.navigation.AppNavGraph
import com.techohns.moneymate.ui.theme.MaliMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaliMateTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}