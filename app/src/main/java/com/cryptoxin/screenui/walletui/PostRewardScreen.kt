package com.cryptoxin.screenui.walletui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoxin.R
import com.cryptoxin.data.models.postrewardm.Data
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.PostRewardS
import com.cryptoxin.viewmodels.PostRewardVM


@Composable
fun PostRewardScreen(postRewardVM: PostRewardVM= hiltViewModel()) {

    val postRewardState = postRewardVM._getPostRewardStateFlow.collectAsState()
    Log.e("1111" , postRewardState.value.toString())


    when (postRewardState.value) {
        is PostRewardS.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize() ,
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp)) ,
                    color = cyellow

                )
            }
        }
        is PostRewardS.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize() ,
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(64.dp)) ,
                    color = chonolulublue
                )
            }
        }
        is PostRewardS.Error -> Text(text = "error")
        is PostRewardS.Loaded -> {
            val postRewardData = (postRewardState.value as PostRewardS.Loaded).data.data


            Column(
                modifier = Modifier.fillMaxWidth() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                postRewardData.forEachIndexed() { index , it ->
                    Column() {
                        PostRewardScreenCard(it)
                    }
                }
            }


        }


    }


}


@Composable
fun PostRewardScreenCard(data: Data) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {
                //cin scan url
            }) ,
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
                painter = painterResource(id = com.cryptoxin.R.drawable.cdarklogo) ,
                alignment = Alignment.CenterStart ,
                contentDescription = "" ,
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier
                    .weight(1f)
            ) {
                Text(
                    text ="${data.amt} CIN" ,
                    color = MaterialTheme.colors.surface ,
                    fontWeight = FontWeight.Bold ,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text =data.username ,
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = MaterialTheme.typography.caption
                )
            }


        }
    }
}