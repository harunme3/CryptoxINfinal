package com.cryptoxin.screenui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.cryptoxin.R
import com.cryptoxin.viewmodels.GetUserViewModel
import com.cryptoxin.viewmodels.ImportWalletViewModel
import com.cryptoxin.viewmodels.WalletVM


@Composable
fun DoneScreen(
){
    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Done1Loader()
        }

        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Done2Loader()
        }

        Text(text = "Now You are Pre-Registered to CryptoxIN", style = TextStyle(fontSize = 18.sp))
        Text(text = "Keep Referring", style = TextStyle(fontSize = 24.sp))

    }

}

@Composable
private fun Done1Loader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congratulations))
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

@Composable
private fun Done2Loader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.done))
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