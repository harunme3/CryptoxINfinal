package com.cryptoxin.screenui.otpless

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun OtpLessScreen(navController: NavController){
//    val context = LocalContext.current
//    val state = rememberWebViewState("https://example.com")
 //   val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://gauge.authlink.me")) }
   val uriHandler = LocalUriHandler.current
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Log.e("1111","clicked")
         uriHandler.openUri(Uri.parse("https://gauge.authlink.me").toString())

        }) {
            Text(text = "Continue to Whatsapp", modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp))
        }
    }


  }