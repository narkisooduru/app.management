package com.example.hospitalmanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hospitalmanagementsystem.navigation.AppNavHost
import com.example.hospitalmanagementsystem.ui.theme.HospitalManagementSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // ✅ Enables Android system splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HospitalManagementSystemTheme {
                AppNavHost()
            }
        }
    }
}