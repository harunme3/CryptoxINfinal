package com.cryptoxin.screenui


import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.cryptoxin.R
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import com.cryptoxin.data.models.nooffollowing.NoOfFollowingM
import com.cryptoxin.data.models.profilem.ProfileM
import com.cryptoxin.navigation.Screens
import com.cryptoxin.screenui.cTopAppBar.CTopAppBar
import com.cryptoxin.screenui.cTopAppBar.CustomShape
import com.cryptoxin.screenui.cTopAppBar.DrawerContent
import com.cryptoxin.screenui.profilescreen.*
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.NoOfFollowerS
import com.cryptoxin.uistate.NoOfFollowingS
import com.cryptoxin.uistate.ProfileS
import com.cryptoxin.viewmodels.NoOfFollowerVM
import com.cryptoxin.viewmodels.NoOfFollowingVM
import com.cryptoxin.viewmodels.ProfileVM
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalLayoutApi::class, ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(navController: NavController,profileVM: ProfileVM= hiltViewModel(),noOfFollowingVM: NoOfFollowingVM= hiltViewModel(),noOfFollowerVM: NoOfFollowerVM= hiltViewModel()) {

    val state = profileVM._getProfileStateFlow.collectAsState()
    val noOfFollowingState = noOfFollowingVM._getNoOfFollowingStateFlow.collectAsState()
    val noOfFollowerState = noOfFollowerVM._getNoOfFollowerStateFlow.collectAsState()

    when (state.value) {

        is ProfileS.Loaded  -> {



            when (noOfFollowingState.value) {

                is NoOfFollowingS.Loaded  -> {



                    when (noOfFollowerState.value) {

                        is NoOfFollowerS.Loaded  -> {

                            val profileData=(state.value as ProfileS.Loaded).data
                            val noOfFollowingData=(noOfFollowingState.value as NoOfFollowingS.Loaded).data
                            val noOfFollowerData=(noOfFollowerState.value as NoOfFollowerS.Loaded).data


                            ProfileScreenComponent(navController,profileData,noOfFollowingData,noOfFollowerData)


                        }
                        else->{
                            Column (modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally){
                                CircularProgressIndicator(
                                    modifier = Modifier.then(Modifier.size(32.dp)),
                                    color = cyellow

                                )
                            }
                        }


                    }




                }
                else->{
                    Column (modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        CircularProgressIndicator(
                            modifier = Modifier.then(Modifier.size(32.dp)),
                            color = cyellow

                        )
                    }
                }


            }




        }
        else->{
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp)),
                    color = cyellow

                )
            }
        }


    }



}




@OptIn(ExperimentalLayoutApi::class , ExperimentalPagerApi::class)
@Composable
fun  ProfileScreenComponent(
    navController: NavController ,
    profileData: ProfileM ,
    noOfFollowingData: NoOfFollowingM ,
    noOfFollowerData: NoOfFollowerM
){

   val darkTheme: Boolean = isSystemInDarkTheme()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
////--------------------background image and circular avtar image----------------------//


        ConstraintLayout(
        ) {
            val (image, avatar,outlineButtonKey,moreIconKey) = createRefs()
            Image(
                painter =painterResource(R.drawable.profilebackground),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .constrainAs(image) {

                    },
                contentScale= ContentScale.Crop
            )

            Image(
                painter =if(profileData.data.Profileimgg=="") painterResource(R.drawable.profiledata) else rememberAsyncImagePainter(
                    model = profileData.data.Profileimgg
                ) ,
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .constrainAs(avatar) {
                        start.linkTo(parent.start , 8.dp)
                        centerAround(image.bottom)
                    }
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Screens.ProfileImageUpdateScreen.route)
                    },
                contentScale= ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color = cblack.copy(alpha = 0.2f) , shape = CircleShape)
                    .constrainAs(moreIconKey) {
                        end.linkTo(parent.end , 8.dp)
                        top.linkTo(parent.top , 8.dp)
                    },
            )
            {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_more_vert_24),
                    contentDescription = null,
                    tint = cwhite,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            OutlinedButton(
                modifier = Modifier.constrainAs(outlineButtonKey) {
                    end.linkTo(parent.end,8.dp)
                    bottom.linkTo(parent.bottom, (-12).dp)
                },
                onClick = {
                    navController.navigate(Screens.EditProfile.route)
                },
                shape = RoundedCornerShape(50)
            )
            {
                Text(
                    text = "Edit Profile",
                    style = TextStyle(
                        color= if(darkTheme) cwhite else cblack,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold

                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 2.dp,)
                )

            }

        }


//////--------------------Edit button----------------------//
//Bio

Column(modifier = Modifier.padding(start = 4.dp)) {

        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
            if(profileData.data.Name.isNotEmpty())
                Text(
                    text = profileData.data.Name ,
                    color= if(darkTheme) cwhite else cblack,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
            if(profileData.data.UserName.isNotEmpty())
                Text(
                    text = profileData.data.UserName ,
                    color = cgraystrong ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }

        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
            if(profileData.data.designation.isNotEmpty())
                Text(
                    text = profileData.data.designation ,
                    color = cgraystrong ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
            if(profileData.data.Organization.isNotEmpty())
                Text(
                    text = profileData.data.Organization ,
                    color = cgraystrong ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
            if(profileData.data.Organization.isNotEmpty())
                Text(
                    text = profileData.data.Dobss ,
                    color = cgraystrong ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth(0.9f)) {
            if(profileData.data.Organization.isNotEmpty())
                Text(
                    text = profileData.data.MailID ,
                    color = cgraystrong ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }
        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
            if(profileData.data.Organization.isNotEmpty())
                Text(
                    text = profileData.data.Otherdetail ,
                    color = cgraystrong ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            else
                Spacer(modifier = Modifier.size(0.dp))
        }
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(modifier = Modifier.clickable{
            navController.navigate(Screens.FollowingScreen.route)
        }) {

            Text(text = noOfFollowingData.data,
                style = TextStyle(
                    color= if(darkTheme) cwhite else cblack,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold

                ),)
            Text(
                text = " Following",
                style = TextStyle(
                    color = cgraystrongest,
                    fontSize = 16.sp
                ),

                )
        }
        Row(modifier = Modifier
            .padding(start = 16.dp)
            .clickable {
                navController.navigate(Screens.FollowerScreen.route)
            }) {

            Text(text = noOfFollowerData.data,
                style = TextStyle(
                  color= if(darkTheme) cwhite else cblack,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(text = " Follower",
                style = TextStyle(
                    color = cgraystrongest,
                    fontSize = 16.sp
                ),

                )
        }
    }
}
////--------------------personal details id----------------------//
////--------------------follower and following----------------------//




////--------------------Tabbar layout----------------------//
        TabLayout()



    }
}
//
//
//
//
//
//
//
////TabLayout()
//
//
@ExperimentalPagerApi
@Composable
fun TabLayout() {

    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState(pageCount = 4)

    // on below line we are creating a column for our widgets.
    Column(
        // for column we are specifying modifier on below line.
        modifier = Modifier.background(Color.White)
    ) {
        // on the below line we are specifying the top app bar
        // and specifying background color for it.

        // on below line we are calling tabs
        Tabs(pagerState = pagerState)
        // on below line we are calling tabs content
        // for displaying our page for each tab layout
        TabsContent(pagerState = pagerState)
    }
}
//
//// on below line we are
//// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf(
        "Tox",
        "Comment" ,
        "Media" ,
        "Likes" ,
    )
    // on below line we are creating
    // a variable for the scope.
    val scope = rememberCoroutineScope()
    // on below line we are creating a
    // individual row for our tab layout.
    TabRow(
        // on below line we are specifying
        // the selected index.
        selectedTabIndex = pagerState.currentPage,

        // on below line we are
        // specifying background color.
        backgroundColor = chonolulublue,

        // on below line we are specifying content color.
        contentColor = Color.White,

        // on below line we are specifying
        // the indicator for the tab
        indicator = { tabPositions ->
            // on below line we are specifying the styling
            // for tab indicator by specifying height
            // and color for the tab indicator.
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        // on below line we are specifying icon
        // and text for the individual tab item
        list.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                // on below line we are specifying icon
                // for each tab item and we are calling
                // image from the list which we have created.

                // on below line we are specifying the text for
                // the each tab item and we are calling data
                // from the list which we have created.
                text = {
                    Text(
                        list[index],

                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold,),
                        // on below line we are specifying the text color
                        // for the text in that tab
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                    )
                },
                // on below line we are specifying
                // the tab which is selected.
                selected = pagerState.currentPage == index,
                // on below line we are specifying the
                // on click for the tab which is selected.
                onClick = {
                    // on below line we are specifying the scope.
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}
//
//// on below line we are creating a tab content method
//// in which we will be displaying the individual page of our tab .
//
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    // on below line we are creating
    // horizontal pager for our tab layout.
    HorizontalPager(state = pagerState) {
        // on below line we are specifying
        // the different pages.
            page ->
        when (page) {
            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            0 -> UserPostScreen()
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            1 -> CommentTabScreen()
            // on below line we are calling tab content screen
            // and specifying data as Settings Screen.
            2 -> MediaTabScreen()

            3 -> LikesTabScreen()
        }
    }
}


