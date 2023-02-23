package com.cryptoxin.screenui


import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.cryptoxin.R
import com.cryptoxin.data.models.allpostm.Data
import com.cryptoxin.navigation.Graph
import com.cryptoxin.navigation.Screens
import com.cryptoxin.screenui.cTopAppBar.CTopAppBar
import com.cryptoxin.screenui.cTopAppBar.CustomShape
import com.cryptoxin.screenui.cTopAppBar.DrawerContent
import com.cryptoxin.ui.theme.cblack
import com.cryptoxin.ui.theme.cgraystrongest
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cyellow
import com.cryptoxin.uistate.AllPostS
import com.cryptoxin.uistate.CreateCommentS
import com.cryptoxin.uistate.DeletePostS
import com.cryptoxin.uistate.LikePostS
import com.cryptoxin.viewmodels.AllPostVM
import com.cryptoxin.viewmodels.DeletePostVM
import com.cryptoxin.viewmodels.LikePostVM
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController , allPostVM: AllPostVM = hiltViewModel() ,

    ) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val allPostState = allPostVM._getAllPostStateFlow.collectAsState()
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = Modifier.fillMaxSize() ,
        scaffoldState = scaffoldState ,
        topBar = {
            CTopAppBar(title = "CryptoxIN" , modifier = Modifier , onNavIconClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            } , onNavIconClick2 = {
                navController.navigate(Screens.ReferralTreeDataScreen.route)
            })
        } ,
        drawerContent = {
            DrawerContent (itemClick = {
                coroutineScope.launch {
                    // delay for the ripple effect
                    delay(timeMillis = 250)
                    scaffoldState.drawerState.close()

//                    if (it == "Referral") {
//                        Toast.makeText(context , "$it" , Toast.LENGTH_SHORT).show()
//                        navController.popBackStack()
//                        navController.navigate(Graph.REFERRAL)
//                    }
//
//                    if (it == "Rewards") {
//                        Toast.makeText(context , "$it" , Toast.LENGTH_SHORT).show()
//                        navController.popBackStack()
//                        navController.navigate(Graph.DAILY_CHECK_IN)
//                    }

                    if (it == "AirDrop") {
                        Toast.makeText(context , "$it" , Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                        val url="https://cinscan.com/"
                        uriHandler.openUri(Uri.parse(url).toString())
                    } else
                    {
                        Toast.makeText(context , "Upcoming Features" , Toast.LENGTH_SHORT).show()
                    }


                }
            })
        } ,
        drawerShape = CustomShape(220.dp , 0f)
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                DoneScreen()
            }
        }
    }

//    val scaffoldState = rememberScaffoldState()
//    val coroutineScope = rememberCoroutineScope()
//    val allPostState = allPostVM._getAllPostStateFlow.collectAsState()
//
//    when (allPostState.value) {
//        is AllPostS.Empty -> {
//            Column (modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally){
//                CircularProgressIndicator(
//                    modifier = Modifier.then(Modifier.size(32.dp)),
//                    color = cyellow
//
//                )
//            }
//        }
//        is AllPostS.Loading -> {
//            Column (modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally){
//                CircularProgressIndicator(
//                    modifier = Modifier.then(Modifier.size(64.dp)),
//                    color = chonolulublue
//                )
//            }
//        }
//        is AllPostS.Error -> Text(text = "error")
//        is AllPostS.Loaded -> {
//            val context= LocalContext.current
//            val clipboardManager: ClipboardManager = LocalClipboardManager.current
//            val data=(allPostState.value as AllPostS.Loaded).data.data
//
//
//            Scaffold(  modifier = Modifier.fillMaxSize(),
//                scaffoldState = scaffoldState,
//                topBar = {
//                    CTopAppBar(title = "CryptoxIN", modifier = Modifier) {
//                        coroutineScope.launch {
//                            scaffoldState.drawerState.open()
//                        }
//                    }
//                },
//                drawerContent = {
//                    DrawerContent {
//                        coroutineScope.launch {
//                            // delay for the ripple effect
//                            delay(timeMillis = 250)
//                            scaffoldState.drawerState.close()
//
//                            if (it=="Referral"){
//                                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
//                                navController.popBackStack()
//                                navController.navigate(Graph.REFERRAL)
//                            }
//
//                            if (it=="Rewards"){
//                                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
//                                navController.popBackStack()
//                                navController.navigate(Graph.DAILY_CHECK_IN)
//                            }
//
//                        }
//                    }
//                },
//                drawerShape = CustomShape(220.dp, 0f)
//                ) {
//                LazyColumn(modifier = Modifier.padding(it)) {
//                    item {
//                        data.forEachIndexed { index , it ->
//                            AllPostCard(
//                                  it,
//                             navController
//                            )
//                        }
//                    }
//                }
//            }
//
//
//        }
//
//
//    }


}


//    @Composable
//    fun AllPostCard(data: Data ,navController: NavController,    deletePostVM: DeletePostVM= hiltViewModel(),
//                    likePostVM: LikePostVM= hiltViewModel()) {
//
//        val contextForToast = LocalContext.current.applicationContext
//
//        val context= LocalContext.current
//        val listItems = listOf<String>("Edit", "Delete","Report")
//        val disabledItem = 2
//        var expanded by remember {
//            mutableStateOf(false)
//        }
//
//        val likePostState = likePostVM._getLikePostStateFlow.collectAsState()
//
//        val deletePostState = deletePostVM._getDeletePostStateFlow.collectAsState()
//
//        if (likePostState.value is LikePostS.Loaded){
//            LaunchedEffect(key1 ="key3"){
//                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        if (deletePostState.value is DeletePostS.Loaded){
//            LaunchedEffect(key1 ="key4"){
//                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth(),
//             elevation = 0.dp,
//    ) {
//        Column() {
//            Divider(modifier = Modifier
//                .padding(vertical = 4.dp)
//                .fillMaxWidth())
//            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
//
//                Image(
//                    modifier = Modifier
//                        .size(60.dp)
//                        .clip(CircleShape),
//                    painter = rememberAsyncImagePainter(model = data.imgHash),
//                    alignment = Alignment.CenterStart,
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                //Main work
//                Column(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//
//                        Row(
//                        ) {
//                            Row {
//                                Text(
//                                    text = data.Name,
//                                    color = cblack,
//                                    style = TextStyle(
//                                        fontSize = 16.sp,
//                                        fontWeight = FontWeight.Bold,
//                                    )
//                                )
//                                Text(
//                                    text = data.usernames,
//                                    color = cgraystrongest,
//                                    style = TextStyle(
//                                        fontSize = 16.sp,
//                                    )
//                                )
//                            }
//
//                            Text(
//                                text = ". 24d",
//                                color = cgraystrongest,
//                                style = TextStyle(
//                                    fontSize = 16.sp,
//                                )
//                            )
//                        }
//                        Spacer(modifier = Modifier.weight(1.0f))
//                        Column {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_baseline_more_vert_24) ,
//                                contentDescription = null ,
//                                modifier = Modifier
//                                    .size(16.dp)
//                                    .clickable {
//                                        expanded = true
//                                    } ,
//                                tint = cgraystrongest
//                            )
//                            // drop down menu
//                            DropdownMenu(
//                                expanded = expanded,
//                                onDismissRequest = {
//                                    expanded = false
//                                }
//                            ) {
//                                // adding items
//                                listItems.forEachIndexed { itemIndex, itemValue ->
//                                    DropdownMenuItem(
//                                        onClick = {
//                                            Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
//                                                .show()
//
//                                                if (itemIndex==0){
//                                                    //edit post
//                                                }
//
//                                                if (itemIndex==1){
//                                                    //delete
//                                                    deletePostVM.getDeletePost(postId = data.allpstId)
//                                                }
//
//                                                if (itemIndex==2){
//                                                    //report post
//                                                }
//
//                                            expanded = false
//                                        },
//                                        enabled = (itemIndex != disabledItem)
//                                    ) {
//                                        Text(text = itemValue,)
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//
//                    Box(modifier = Modifier.clickable {
//                        navController.navigate(Screens.ViewPostScreen.route+"/${data.allpstId}")
//                    }) {
//
//                        RichText(
//                            modifier = Modifier.padding(16.dp),
//
//                        ) {
//                            Markdown(
//                                content=data.content,
//                            )
//                        }
//
//
//                    }
//                    Image(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp),
//                        painter = rememberAsyncImagePainter(model = data.imgHash),
//                        alignment = Alignment.CenterStart,
//                        contentDescription = "",
//                        contentScale = ContentScale.Crop
//                    )
//
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Row {
//                            Icon(
//                                painter = painterResource(id = R.drawable.comment) ,
//                                contentDescription = null ,
//                                modifier = Modifier
//                                    .size(24.dp)
//                                    .padding(start = 8.dp)
//                                    .clickable {
//
//                                        navController.navigate(Screens.CreateCommentScreen.route + "/${data.allpstId}")
//
//                                    } ,
//
//                                )
//                            Row {
//                                Spacer(modifier = Modifier.width(4.dp))
//                            }
//                            Text(text = data.CommentCount)
//                        }
//                        Row {
//                            Icon(
//                                painter = painterResource(id = R.drawable.retweet) ,
//                                contentDescription = null ,
//                                modifier = Modifier
//                                    .size(24.dp)
//                                    .padding(start = 8.dp) ,
//
//                                )
//                            Spacer(modifier = Modifier.width(4.dp))
//                            Text(text = data.reportCount)
//                        }
//                        Row {
//                            Icon(
//                                painter = painterResource(id = R.drawable.like) ,
//                                contentDescription = null ,
//                                modifier = Modifier
//                                    .size(24.dp)
//                                    .padding(start = 8.dp)
//                                    .clickable {
//                                        Log.d("1111" , "like call")
//                                        likePostVM.getLikePost(postId = data.allpstId)
//                                    } ,
//
//                                )
//                            Spacer(modifier = Modifier.width(4.dp))
//                            Text(text = data.likeCount)
//                        }
//                        Icon(
//                            painter = painterResource(id = R.drawable.share),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(24.dp)
//                                .padding(start = 8.dp),
//
//                            )
//
//                    }
//                }
//
//
//            }
//        }
//    }
//
//
//}

