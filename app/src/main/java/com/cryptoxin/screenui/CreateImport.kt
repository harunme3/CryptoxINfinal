package com.cryptoxin.screenui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cryptoxin.R
import com.cryptoxin.navigation.Screens
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite

@OptIn(ExperimentalTextApi::class)
@Composable
fun CreateImport(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.cenvironmentnew),
                contentDescription = "CLogo",
            )
            Image(
                painter = painterResource(id = R.drawable.cenvironment),
                contentDescription = "CEnvironment",
            )
            Button(
                onClick = {
                    navController.navigate(Screens.CreateAccount.route)
                },
                modifier = Modifier.padding(30.dp).clip(RoundedCornerShape(topEnd = 36.dp, bottomStart = 36.dp,)),
                colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)

            )
            {
                Text(text = "Create Account",
                    style = TextStyle(
                        color = cwhite,
                        fontSize = 18.sp

                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                )

            }

            Button(
                onClick = {
                    navController.navigate(Screens.ImportAccount.route)
                },
                modifier = Modifier.padding(30.dp).clip(RoundedCornerShape(topEnd = 36.dp, bottomStart = 36.dp,)),
                colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)

            )
            {
                Text(text = "Import Account",
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



}