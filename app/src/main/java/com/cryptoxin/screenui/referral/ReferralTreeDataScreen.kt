package com.cryptoxin.screenui.referral

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoxin.R
import com.cryptoxin.ui.theme.cblack
import com.cryptoxin.ui.theme.cgraystrongest
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.uistate.DirectReferralS
import com.cryptoxin.viewmodels.DirectReferralVM


@Composable
fun ReferralTreeDataScreen(navController: NavController,directReferralVM: DirectReferralVM= hiltViewModel()){

    val context= LocalContext.current
    val directReferralState = directReferralVM._getDirectReferralStateFlow.collectAsState()

    var btntextreferral by remember {
        mutableStateOf("Submit")
    }
    var referralcode by remember {
        mutableStateOf("")
    }

    var address by remember { mutableStateOf(TextFieldValue("")) }
    Log.e("1111",directReferralState.value.toString())


    Column(modifier = Modifier
        .fillMaxSize().background(color = cwhite)
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,) {

        Box() {

            OutlinedTextField(
                modifier = Modifier.padding(horizontal = 24.dp),
                label = {
                    Text(
                        text = "Enter Referral Address",
                        style = TextStyle(
                            color = cblack ,
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = chonolulublue ,
                    unfocusedBorderColor = chonolulublue ,
                    focusedLabelColor = cblack ,
                    cursorColor = chonolulublue ,
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
                directReferralVM.getDirectReferral(myAddress = referralcode)

            },
            modifier = Modifier
                .padding(30.dp)
                .clip(RoundedCornerShape(topEnd = 36.dp , bottomStart = 36.dp ,)),
            colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)
        )
        {

            Text(text = btntextreferral,
                style = TextStyle(
                    color = cwhite ,
                    fontSize = 18.sp

                ) ,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )



        }

        Divider()

        if (directReferralState.value is DirectReferralS.Loaded){

            val data=(directReferralState.value as DirectReferralS.Loaded).data.data
            btntextreferral="Submit"
            if (data.isEmpty()){
                Toast.makeText(context, "This Address have not referred anyone", Toast.LENGTH_SHORT).show()
            }


            LazyColumn() {
                item {
                    data.forEachIndexed { index , it ->
                        AddressCard(
                            it,
                        )
                    }
                }
            }

        }



    }

}


@Composable
fun AddressCard(data: String) {

  val context= LocalContext.current
  val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Column {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 8.dp)
             ,
            elevation = 0.dp ,
            backgroundColor = cgraystrongest
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp) ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape) ,
                    painter = painterResource(id = R.drawable.cdarklogo) ,
                    alignment = Alignment.CenterStart ,
                    contentDescription = "" ,
                    contentScale = ContentScale.Crop
                )

                Column(
                    Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = data,

                        style =TextStyle(
                            color = cwhite,
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_content_copy_24) ,
                        contentDescription = null ,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 8.dp).clickable {
                                clipboardManager.setText(AnnotatedString((data)))
                                Toast.makeText(context, "$data Copied", Toast.LENGTH_SHORT).show()
                            } ,
                    )
                }


            }
        }

        Divider()
    }
}