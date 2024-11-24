package com.saputroekosulistiyo.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.saputroekosulistiyo.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeTheme {
                CupcakeApp()
            }
        }
    }
}
