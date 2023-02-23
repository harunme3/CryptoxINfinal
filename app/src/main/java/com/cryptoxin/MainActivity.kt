package com.cryptoxin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.cryptoxin.navigation.RootNavigationGraph
import com.cryptoxin.ui.theme.CryptoxinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoxinTheme{
                RootNavigationGraph(rememberNavController())
            }
        }
    }
}

