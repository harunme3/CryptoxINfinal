package com.cryptoxin.screenui.homeui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cryptoxin.R
import com.cryptoxin.data.models.commentsm.Data
import com.cryptoxin.data.models.specificpostm.SpecificPostM
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.CommentsS
import com.cryptoxin.uistate.SpecificPostS
import com.cryptoxin.viewmodels.*
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText


@Composable
fun ViewPostScreen(navController:NavController , postId: String, specificPostVM: SpecificPostVM = hiltViewModel() , commentsVM: CommentsVM = hiltViewModel() , createCommentVM: CreateCommentVM = hiltViewModel()) {


      LaunchedEffect(key1 = "key1" ){
          Log.e("2222",postId)
          specificPostVM.getSpecificPost(postId=postId)
          commentsVM.getComments(postId=postId)
      }
    val specificPostState = specificPostVM._getSpecificPostStateFlow.collectAsState()
    val commentsState = commentsVM._getCommentsStateFlow.collectAsState()


    when (specificPostState.value) {
        is SpecificPostS.Empty -> {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp)),
                    color = cyellow

                )
            }
        }
        is SpecificPostS.Loading -> {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(64.dp)),
                    color = chonolulublue
                )
            }
        }
        is SpecificPostS.Error -> Text(text = "error")
        is SpecificPostS.Loaded -> {




            when (commentsState.value) {
                is CommentsS.Empty -> {
                    Column (modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        CircularProgressIndicator(
                            modifier = Modifier.then(Modifier.size(32.dp)),
                            color = cyellow

                        )
                    }
                }
                is CommentsS.Loading -> {
                    Column (modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        CircularProgressIndicator(
                            modifier = Modifier.then(Modifier.size(64.dp)),
                            color = chonolulublue
                        )
                    }
                }
                is CommentsS.Error -> Text(text = "error")
                is CommentsS.Loaded -> {


                    val specificPostData=(specificPostState.value as SpecificPostS.Loaded).data
                    val commentsData=(commentsState.value as CommentsS.Loaded).data.data


              
                    LazyColumn() {

                        item {
                            ViewPostCard(specificPostData)
                        }
                        item {
                            commentsData.forEachIndexed { index , it ->
                              ViewPostScreenCard(it)
                            }
                        }
                    }

                }


            }


        }


    }


}


@Composable
fun ViewPostCard(
    specificPostData: SpecificPostM,
    deletePostVM: DeletePostVM= hiltViewModel(),
    likePostVM: LikePostVM= hiltViewModel()
) {

    val contextForToast = LocalContext.current.applicationContext
    val listItems = listOf<String>("Edit", "Delete","Report")
    val disabledItem = 2
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Column() {
            Divider(modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth())
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {

                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(model = specificPostData.data[0].imgHash) ,
                    alignment = Alignment.CenterStart,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(4.dp))
                //Main work
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Row(
                        ) {
                            Row {
                                Text(
                                    text = specificPostData.data[0].Name,
                                    color = cblack,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Text(
                                    text = specificPostData.data[0].username,
                                    color = cgraystrongest ,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                    )
                                )
                            }

                            Text(
                                text = ". 24d",
                                color = cgraystrongest ,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1.0f))
                        Column {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_more_vert_24) ,
                                contentDescription = null ,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {
                                        expanded = true
                                    } ,
                                tint = cgraystrongest
                            )
                            // drop down menu
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                // adding items
                                listItems.forEachIndexed { itemIndex, itemValue ->
                                    DropdownMenuItem(
                                        onClick = {
                                            Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
                                                .show()

                                            if (itemIndex==0){
                                                //edit post
                                            }

                                            if (itemIndex==1){
                                                //delete
                                                deletePostVM.getDeletePost(postId = specificPostData.data[0].pstId)
                                            }

                                            if (itemIndex==2){
                                                //report post
                                            }

                                            expanded = false
                                        },
                                        enabled = (itemIndex != disabledItem)
                                    ) {
                                        Text(text = itemValue,)
                                    }
                                }
                            }
                        }
                    }


                    Box() {

                        RichText(
                            modifier = Modifier.padding(16.dp),

                            ) {
                            Markdown(
                                content=specificPostData.data[0].content,
                            )
                        }


                    }
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        painter = rememberAsyncImagePainter(model = specificPostData.data[0].imgHash) ,
                        alignment = Alignment.CenterStart,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.comment) ,
                                contentDescription = null ,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 8.dp) ,

                                )
                            Row {
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                            Text(text = specificPostData.data[0].CommentCount)
                        }
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.retweet) ,
                                contentDescription = null ,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 8.dp) ,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = specificPostData.data[0].reportCount)
                        }
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.like) ,
                                contentDescription = null ,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 8.dp)
                                    .clickable {
                                        likePostVM.getLikePost(postId = specificPostData.data[0].pstId)
                                    } ,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = specificPostData.data[0].likeCount)
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.share) ,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(start = 8.dp),

                            )



                    }
                }


            }
        }
    }


}


@Composable
fun ViewPostScreenCard(data: Data) {

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
        backgroundColor = cgraystronglight
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
                    text = data.reciver ,
                    color = MaterialTheme.colors.surface ,
                    fontWeight = FontWeight.Bold ,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = data.messages ,
                    modifier = Modifier.padding(0.dp , 0.dp , 12.dp , 0.dp) ,
                    color = cwhite ,
                    style = MaterialTheme.typography.caption
                )
            }

        }
    }
}
