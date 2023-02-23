package com.cryptoxin.appversion

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.cryptoxin.R
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.viewmodels.AppVersionVM


@Composable
fun AppVersionScreen() {



    Scaffold(
        modifier = Modifier.fillMaxSize() ,
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                AppVersionComponent()
            }
        }
    }
}

@Composable
fun AppVersionComponent(
){



    val uriHandler = LocalUriHandler.current
    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
        ) {
            AppVersionLottie()
        }

        Text(text =  "Your App is not up to date Update your app", style = TextStyle(fontSize = 18.sp))
   Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Button(
            onClick = {
            val url="https://play.google.com/store/apps/details?id=com.cryptoxin"
            uriHandler.openUri(Uri.parse(url).toString())
        }) {
            Text(text = "Update", modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp))
        }

    }

}

@Composable
private fun AppVersionLottie() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.update))
    val progress by animateLottieCompositionAsState(
        composition ,
        isPlaying = true ,
        reverseOnRepeat = true ,
        iterations = LottieConstants.IterateForever ,
    )
    LottieAnimation(
        composition = composition ,
        progress = { progress } ,
    )

}