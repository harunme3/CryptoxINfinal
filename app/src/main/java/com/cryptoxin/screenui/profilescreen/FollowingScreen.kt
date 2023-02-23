package com.cryptoxin.screenui.profilescreen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoxin.R
import com.cryptoxin.data.models.followingm.Data
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.FollowS
import com.cryptoxin.uistate.FollowingS
import com.cryptoxin.uistate.UnFollowS
import com.cryptoxin.viewmodels.FollowingVM
import com.cryptoxin.viewmodels.UnFollowVM


@Composable
fun FollowingScreen(
 navController: NavController ,
 followingVM: FollowingVM = hiltViewModel(),
 unFollowVM: UnFollowVM= hiltViewModel()
){


    val followingState = followingVM._getFollowingStateFlow.collectAsState()
    Log.e("1111",followingState.value.toString())


    val unFollowState = unFollowVM._getUnFollowStateFlow.collectAsState()

    val context=LocalContext.current
    Log.e("1112", unFollowState.value.toString())

    if(unFollowState.value is UnFollowS.Loaded)
    {
        LaunchedEffect(key1 = "key1" ){
            Toast.makeText(context, "Followed", Toast.LENGTH_LONG).show()
        }

    }





    when (followingState.value) {
        is FollowingS.Empty -> {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp)),
                    color = cyellow

                )
            }
        }
        is FollowingS.Loading -> {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(64.dp)),
                    color = chonolulublue
                )
            }
        }
        is FollowingS.Error -> Text(text = "error")
        is FollowingS.Loaded -> {
            val context= LocalContext.current
            val clipboardManager: ClipboardManager = LocalClipboardManager.current
            val data=(followingState.value as FollowingS.Loaded).data.data
            Toast.makeText(context, "${data}", Toast.LENGTH_SHORT).show()

            Box(
            ) {
                LazyColumn(){
                    item {
                        data.forEachIndexed { index, it ->
                            FollowingCardComponent(it, modifier = Modifier.clickable {
                                unFollowVM.getUnFollow(friendAddress = it.useraddress)
                            })
                        }
                    }
                }

            }


        }


    }

}






//----------------New card------------------------//


@Composable
fun FollowingCardComponent(data: Data,modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)) ,
        elevation = 0.dp ,
        backgroundColor = cgraystrongest
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape) ,
                painter = painterResource(id = R.drawable.dummyprofilephoto),
                alignment = Alignment.CenterStart ,
                contentDescription = "" ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = data.Name ,
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite,
                    fontWeight = FontWeight.Bold ,
                    style = MaterialTheme.typography.subtitle1,

                    )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text ="@${data.UserName}",
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text ="${data.designation}",
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text ="${data.Organization}",
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = MaterialTheme.typography.caption
                )


            }
            Row(
                modifier = Modifier.fillMaxSize() ,
                horizontalArrangement = Arrangement.End ,
                verticalAlignment = Alignment.CenterVertically

            ) {
                FollowingCardChipView(text = "Following" , colorResource = chonolulublue, modifier = modifier)
            }
        }
    }
}


@Composable
fun FollowingCardChipView(text: String , colorResource: Color,modifier: Modifier) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = cwhite
            )
    ) {
        Text(
            text = text , modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp) ,
            style = TextStyle(fontWeight = FontWeight.Bold) ,
            color = colorResource
        )
    }
}