package com.cryptoxin.screenui.homeui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.uistate.CreateCommentS
import com.cryptoxin.viewmodels.CreateCommentVM


@Composable
fun CreateCommentScreen(navController: NavController, postId: String,createCommentVM: CreateCommentVM= hiltViewModel()){
    val createCommentState = createCommentVM._getCreateCommentStateFlow.collectAsState()


    var comment by remember {
        mutableStateOf("")
    }


    Log.e("1111",createCommentState.value.toString())

    if (createCommentState.value is CreateCommentS.Loaded){
        LaunchedEffect(key1 ="key1"){
            navController.popBackStack()
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = comment, onValueChange = {
            comment = it
             }
        )

        Button(
            onClick = {
           createCommentVM.getCreateComment(postId=postId, messages =comment)

            },
            modifier = Modifier
                .padding(30.dp)
                .clip(RoundedCornerShape(topEnd = 36.dp , bottomStart = 36.dp ,)),
            colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)
        )
        {

            Text(text = "Comment",
                style = TextStyle(
                    color = cwhite ,
                    fontSize = 18.sp
                ) ,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )


        }
    }
}