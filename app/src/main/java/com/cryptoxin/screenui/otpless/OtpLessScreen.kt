package com.cryptoxin.screenui.otpless

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import com.cryptoxin.R
import com.cryptoxin.databinding.ActivityGraphBinding


@Composable
fun OtpLessScreen(navController: NavController){
Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
Text(text = "few")
//    AndroidView(
//        factory = { context ->
//            val view = LayoutInflater.from(context).inflate(R.layout.otp_less, null, false)
//            val button =view.findViewById<View>(R.id.whatsapp_login) as WhatsappLoginButton
//            button.setResultCallback { data: OtplessResponse ->
//                Log.e("1111",data.toString())
//                val waid = data.waId
//
//            }
//            view // return the view
//        },
//        update = { view ->
//            // Update the view
//        }
//    )

    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.activity_graph, null, false)

            view // return the view
        },
        update = { view ->
            // Update the view
        }
    )

//    AndroidViewBinding(ActivityGraphBinding::inflate){
//
//    }


}
  }