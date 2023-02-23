package com.cryptoxin.screenui.createImport

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.cryptoxin.R
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.navigation.Graph
import com.cryptoxin.ui.theme.cblack
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.uistate.CheckUpdateUserNameS
import com.cryptoxin.uistate.ProfileUpdateS
import com.cryptoxin.uistate.WalletIdS
import com.cryptoxin.viewmodels.CheckUpdateUserNameVM
import com.cryptoxin.viewmodels.ProfileUpdateInitialVM
import com.cryptoxin.viewmodels.WalletVM

@Composable
fun MandatoryDetails(
     navController: NavController,
     mnemonic: String,
     privateKey: String,
     address: String,
     referralCode: String,
     profileUpdateInitialVM: ProfileUpdateInitialVM= hiltViewModel(),
     walletVM: WalletVM = hiltViewModel(),
     checkUpdateUserNameVM: CheckUpdateUserNameVM= hiltViewModel()
){

    val checkUpdateUserNameState = checkUpdateUserNameVM._getCheckUpdateUserNameStateFlow.collectAsState()
    val state = profileUpdateInitialVM._setProfileUpdateInitialStateFlow.collectAsState()
    val context=LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Log.e("3333",state.value.toString())

    var btntextmand by remember {
        mutableStateOf("Submit")
    }

    var name by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }


    Log.e("1111",state.value.toString())

    if (checkUpdateUserNameState.value is CheckUpdateUserNameS.Loaded){
        LaunchedEffect(key1 ="key1"){
            val checkUpdateUserNameData = (checkUpdateUserNameState.value as CheckUpdateUserNameS.Loaded).data
           if (checkUpdateUserNameData.data){
               profileUpdateInitialVM.setProfileUpdate(
                   myAddress =address ,
                   privateKey=privateKey,
                   name = name.trim() ,
                   UserName ="@${username}",
                   email = "" ,
                   organization = "" ,
                   profileTag = "" ,
                   designation = "" ,
                   dob = "" ,
                   otherDetails = "" ,
                   Profileimgg = "" ,
                   backgroundimgg = ""
               )
           }
            else{
               btntextmand="Submit"
               Toast.makeText(context, "UserName Already Taken", Toast.LENGTH_SHORT).show()
            }
        }

    }

    if (state.value is ProfileUpdateS.Loaded){
        LaunchedEffect(key1 ="key1"){
           walletVM.createWallet(WalletEntity(null,mnemonicPhrase=mnemonic,privateKey=privateKey,address=address))
            navController.navigate(Graph.DASHBOARD)
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            MandatoryDetailsLoaderImport()
        }


        OutlinedTextField(
            modifier =Modifier.padding(horizontal = 24.dp),
            label = {
                Text(
                    text = "Enter Full Name",
                    style = TextStyle(
                        color = cblack ,
                    )
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = chonolulublue,
                unfocusedBorderColor = chonolulublue,
                focusedLabelColor = cblack ,
                cursorColor =chonolulublue,
                textColor = cblack
            ),
            value = name,
            onValueChange = { name = it },
        )
        Spacer(modifier = Modifier.height(24.dp))



        OutlinedTextField(
            modifier =Modifier.padding(horizontal = 24.dp),
            label = {
                Text(
                    text = "Enter User Name",
                    style = TextStyle(
                        color = cblack ,
                    )
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = chonolulublue,
                unfocusedBorderColor = chonolulublue,
                focusedLabelColor = cblack ,
                cursorColor =chonolulublue,
                textColor = cblack
            ),
            value = username,
            onValueChange = { username = it },
        )



        Button(

            onClick = {
                val regularExpression = "^[A-Za-z][A-Za-z0-9_.]{6,20}$"

                if (name.isEmpty()){
                    Toast.makeText(context, "Please Enter Full Name", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (username.isEmpty()){
                    Toast.makeText(context, "Please Enter User Name", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (username.isNotEmpty()){

                    if (username.matches(regularExpression.toRegex())) {
                     Log.d("1111","It is valid username")
                    } else {
                        Toast.makeText(context, "Please Enter Valid User Name", Toast.LENGTH_SHORT).show()
                        return@Button
                    }


                }


                btntextmand="Submitting"
                checkUpdateUserNameVM.getCheckUpdateUserName(myAddress = address, privateKey = privateKey, userName = username)


            },
            modifier = Modifier
                .padding(30.dp)
                .clip(RoundedCornerShape(topEnd = 36.dp , bottomStart = 36.dp)).clickable {

                },
            colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)
        )
        {

            Text(text = btntextmand,
                style = TextStyle(
                    color = cwhite,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )

        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "User Name Format :" ,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Username must have 6 character" ,
        )

    }


}

@Composable
private fun MandatoryDetailsLoaderImport() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.profile))
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