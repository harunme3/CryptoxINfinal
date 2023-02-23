package com.cryptoxin.screenui.walletui

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoxin.data.models.trxhistorym.Result
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.TrxHistoryS
import com.cryptoxin.viewmodels.TrxHistoryVM


@Composable
fun TransactionHistoryScreen(trxHistoryVM: TrxHistoryVM= hiltViewModel()) {

    val trxHistoryState = trxHistoryVM._getTrxHistoryStateFlow.collectAsState()
    Log.e("1111" , trxHistoryState.value.toString())


    when (trxHistoryState.value) {
        is TrxHistoryS.Empty -> {
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
        is TrxHistoryS.Loading -> {
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
        is TrxHistoryS.Error -> Text(text = "error")
        is TrxHistoryS.Loaded -> {
            val trxHistoryData = (trxHistoryState.value as TrxHistoryS.Loaded).data.msg.result


            Column(
                modifier = Modifier.fillMaxWidth() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                trxHistoryData.forEachIndexed() { index , it ->

                        TransactionHistoryCard(it)

                }
            }


        }


    }


}


@Composable
fun TransactionHistoryCard(result: Result) {
    val uriHandler = LocalUriHandler.current
    val darkTheme: Boolean = isSystemInDarkTheme()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {
               val url="https://cinscan.com/tx/${result.hash}"
                uriHandler.openUri(Uri.parse(url).toString())
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
                    text ="${result.value} CIN" ,
                    color= if(darkTheme) cwhite else cblack,
                    fontWeight = FontWeight.Bold ,
                    style = typography.subtitle1
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text =result.hash ,
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = typography.caption
                )
            }

            
        }
    }
}
