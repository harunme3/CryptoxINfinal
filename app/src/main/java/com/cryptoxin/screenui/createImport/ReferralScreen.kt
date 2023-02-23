package com.cryptoxin.screenui.createImport

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.cryptoxin.R
import com.cryptoxin.navigation.Screens
import com.cryptoxin.ui.theme.cblack
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.uistate.CinBalanceS
import com.cryptoxin.uistate.RegisterState
import com.cryptoxin.viewmodels.RegisterViewModel
import com.cryptoxin.viewmodels.WalletVM

@Composable
fun ReferralScreen(
    navController: NavController,
     mnemonic: String,
    privateKey: String,
    address: String,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    walletVM: WalletVM = hiltViewModel()
){
    val state = registerViewModel._registrationStateFlow.collectAsState()

    val context= LocalContext.current

    var btntextreferral by remember {
        mutableStateOf("Submit")
    }
    var referralcode by remember {
        mutableStateOf("")
    }
    Log.e("1111",state.value.toString())

    if (state.value is RegisterState.Loaded){
        LaunchedEffect(key1 ="key1"){
            Log.e("1111",state.value.toString())
            val data=(state.value as RegisterState.Loaded).data
            Log.e("1111",data.toString())
            if (!data.msg){
                btntextreferral="Submit"
                Toast.makeText(context, "Please use CryptoxIn referral code", Toast.LENGTH_SHORT).show()
            }
            else
            {
                navController.navigate(Screens.MandatoryDetails.route+"/${mnemonic}/${privateKey}/${address}/${referralcode}")

            }

        }

    }




    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        Box(modifier = Modifier.height(400.dp).fillMaxWidth()){
            LoaderReferral()
        }

          Box() {

              OutlinedTextField(
                  modifier =Modifier.padding(horizontal = 24.dp),
                  label = {
                      Text(
                          text = "Enter Referral Address",
                          style = TextStyle(
                              color = cblack,
                          )
                      )
                  },
                  colors = TextFieldDefaults.outlinedTextFieldColors(
                      focusedBorderColor = chonolulublue,
                      unfocusedBorderColor = chonolulublue,
                      focusedLabelColor = cblack,
                      cursorColor =chonolulublue,
                      textColor = cblack
                  ),
                  value = referralcode,
                  onValueChange = { referralcode = it },
              )


}



    Button(
        onClick = {

            if (referralcode.length<42){
                Toast.makeText(context, "Enter Correct Referral Address", Toast.LENGTH_SHORT).show()
                return@Button
            }


            btntextreferral="Submitting"
            registerViewModel.registrationCall(
                myAddress = address,
                privateKey = privateKey,
                referralCode = referralcode
            )

        },
        modifier = Modifier
            .padding(30.dp)
            .clip(RoundedCornerShape(topEnd = 36.dp , bottomStart = 36.dp ,)),
        colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)
    )
    {

        Text(text = btntextreferral,
            style = TextStyle(
                color = cwhite,
                fontSize = 18.sp

            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )



    }


    }
    
}

@Composable
private fun LoaderReferral() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.referral))
    val progress by animateLottieCompositionAsState(composition,
        isPlaying = true,
        reverseOnRepeat=true,
        iterations = LottieConstants.IterateForever,)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )

}
