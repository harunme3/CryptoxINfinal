package com.cryptoxin.screenui.referral

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoxin.R
import com.cryptoxin.data.models.postrewardm.Data
import com.cryptoxin.ui.theme.cgraystrongest
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.ui.theme.cyellow
import com.cryptoxin.uistate.FiftyLevelRewardS
import com.cryptoxin.uistate.PostRewardS
import com.cryptoxin.viewmodels.FiftyLevelRewardVM
import com.cryptoxin.viewmodels.PostRewardVM






@Composable
fun AffiliateRewardScreen(fiftyLevelRewardVM: FiftyLevelRewardVM = hiltViewModel()) {

    val fiftyLevelRewardState = fiftyLevelRewardVM._getFiftyLevelRewardStateFlow.collectAsState()
    Log.e("1111" , fiftyLevelRewardState.value.toString())


    when (fiftyLevelRewardState.value) {
        is FiftyLevelRewardS.Empty -> {
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
        is FiftyLevelRewardS.Loading -> {
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
        is FiftyLevelRewardS.Error -> Text(text = "error")
        is FiftyLevelRewardS.Loaded -> {
            val postRewardData = (fiftyLevelRewardState.value as FiftyLevelRewardS.Loaded).data.data


            Column(
                modifier = Modifier.fillMaxWidth() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                (1..10).forEachIndexed() { index , it ->
                    Column() {
                        AffiliateRewardScreenCard(it)
                    }
                }
            }


        }


    }


}


@Composable
fun AffiliateRewardScreenCard(any: Any) {

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
                    text ="${0} CIN" ,
                    color = MaterialTheme.colors.surface ,
                    fontWeight = FontWeight.Bold ,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text ="data.username" ,
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = MaterialTheme.typography.caption
                )
            }


        }
    }
}