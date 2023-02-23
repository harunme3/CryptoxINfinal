package com.cryptoxin.screenui.walletui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.cryptoxin.R
import com.cryptoxin.uistate.CinSendS
import com.cryptoxin.viewmodels.CinSendVM


@Composable
fun CinSendScreen(navController: NavController,cinSendVM: CinSendVM= hiltViewModel()){
    val cinSendState = cinSendVM._getCinSendStateFlow.collectAsState()
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var btnsend by remember {
        mutableStateOf("Send")
    }

    Log.e("1111",cinSendState.value.toString())
    val context= LocalContext.current
    if (cinSendState.value is CinSendS.Loaded){
        LaunchedEffect(key1 ="key1"){
            navController.popBackStack()
        }

    }

    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
       ) {

        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            LoaderSend()
        }

        OutlinedTextField(
            modifier =Modifier.padding(horizontal = 24.dp),
            label = {
                Text(
                    text = "Enter Receiver Address",
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                    )
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.primaryVariant
            ),
            value = address,
            onValueChange = { address = it },
        )

        OutlinedTextField(
            label = {
                Text(
                    text = "Enter Amount",
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                    )
                )
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.primaryVariant
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            value = amount,
            onValueChange = { amount = it },
        )
Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = {

                if (address.text.length<42){
                    Toast.makeText(context, "Enter Correct Address", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                if (amount.text.isEmpty()){
                    Toast.makeText(context, "Please Enter Amount", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }



                btnsend="Sending"
                cinSendVM.getCinSend(receiver = address.text.toString(),amount=amount.text.toString())
            }
        ) {
            Text(text=btnsend,    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),)
        }




    }
    }

@Composable
private fun LoaderSend() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.send))
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
