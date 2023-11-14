
package com.emanuelcchagas.q_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emanuelcchagas.q_manager.ui.theme.QManagerTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QManagerTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}