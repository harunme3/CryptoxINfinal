package com.cryptoxin.screenui.walletui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cryptoxin.ui.theme.cdarkred
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite



@Composable
fun PrivateKeyScreen(navController: NavController,privateKey:String){

    val context= LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp)){
        Box(
            modifier = Modifier
                .background(color = Color.Red.copy(alpha = 0.1f))
                .padding(8.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "DO NOT share your Private Key to anyone as this gives full access to your wallet",
                    style = TextStyle(
                        color = cdarkred ,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ) ,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                Text(
                    text = privateKey,
                    style = TextStyle(
                        color = cdarkred ,
                        fontSize = 14.sp

                    ) ,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }

        Button(
            onClick = {
                clipboardManager.setText(AnnotatedString((privateKey)))
                Toast.makeText(context, "PrivateKey Copied", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)
        )
        {
            Text(
                text = "Copy",
                style = TextStyle(
                    color = cwhite ,
                    fontSize = 18.sp

                ) ,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            )

        }
    }


}