package com.cryptoxin.screenui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoxin.R
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.models.cinbalancem.CinBalanceM
import com.cryptoxin.navigation.Screens
import com.cryptoxin.screenui.walletui.CommentRewardScreen
import com.cryptoxin.screenui.walletui.LikeRewardScreen
import com.cryptoxin.screenui.walletui.PostRewardScreen
import com.cryptoxin.screenui.walletui.TransactionHistoryScreen
import com.cryptoxin.ui.theme.cblack
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.ui.theme.cyellow
import com.cryptoxin.uistate.CinBalanceS
import com.cryptoxin.uistate.WalletIdS
import com.cryptoxin.viewmodels.CinBalanceVM
import com.cryptoxin.viewmodels.WalletVM
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@Composable
fun WalletScreen(
    navController: NavController , walletVM: WalletVM = hiltViewModel() ,
    cinBalanceVM: CinBalanceVM = hiltViewModel()
) {


    LaunchedEffect(Unit) {
        walletVM.getWallet(walletId = 1)
        cinBalanceVM.getCinBalance()
    }
    val walletState = walletVM._getWalletStateFlow.collectAsState()
    val cinBalanceState = cinBalanceVM._getCinBalanceStateFlow.collectAsState()

    Log.d("1111" , walletState.value.toString())
    Log.d("1111" , cinBalanceState.value.toString())


    when (walletState.value) {
        is WalletIdS.Empty -> {
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
        is WalletIdS.Loading -> {
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
        is WalletIdS.Error -> Text(text = "error")
        is WalletIdS.Loaded -> {

            when (cinBalanceState.value) {
                is CinBalanceS.Empty -> {
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
                is CinBalanceS.Loading -> {
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
                is CinBalanceS.Error -> Text(text = "error")
                is CinBalanceS.Loaded -> {
                    val walletData = (walletState.value as WalletIdS.Loaded).data
                    val CinBalanceData = (cinBalanceState.value as CinBalanceS.Loaded).data
                    WalletComponent(walletData , CinBalanceData , navController)

                }


            }

        }


    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WalletComponent(
    walletData: WalletEntity ,
    CinBalanceData: CinBalanceM ,
    navController: NavController ,
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val darkTheme: Boolean = isSystemInDarkTheme()
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(8.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 24.dp ,
                        topEnd = 24.dp ,
                        bottomStart = 24.dp ,
                        bottomEnd = 24.dp
                    )
                )
                .background(color = chonolulublue)
        ) {


            Column() {

                //upper
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .height(160.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 24.dp ,
                                topEnd = 24.dp ,
                                bottomStart = 24.dp ,
                                bottomEnd = 24.dp
                            )
                        )
                        .background(color = cwhite)
                ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween ,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {

                        //upper
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween ,
                            modifier = Modifier.fillMaxWidth() ,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.width(120.dp)) {
                                    Text(
                                        text = walletData.address ,
                                        style = TextStyle(
                                            fontSize = 8.sp ,
                                            color = if (darkTheme) cblack else cblack
                                        ) ,
                                        maxLines = 2
                                    )
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.ic_baseline_content_copy_24) ,
                                    contentDescription = null ,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .padding(start = 8.dp)
                                        .clickable {
                                            clipboardManager.setText(AnnotatedString((walletData.address)))
                                            Toast
                                                .makeText(context , "Copied" , Toast.LENGTH_SHORT)
                                                .show()
                                        } ,
                                )
                            }

                            Row() {

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_lock_open_24) ,
                                    contentDescription = null ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable {
                                            navController.navigate(Screens.PrivateKeyScreen.route + "/${walletData.privateKey}")
                                        } ,
                                    tint = chonolulublue
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_screen_share_24) ,
                                    contentDescription = null ,
                                    modifier = Modifier
                                        .clickable {
                                            val sendIntent: Intent = Intent().apply {
                                                action = Intent.ACTION_SEND
                                                putExtra(
                                                    Intent.EXTRA_TEXT ,
                                                    "Earn 1\$ worth of CIN Coins just by signing up.\nCryptoxIN is the world's first decentralised Crypto Social community, connecting advertisers and users. \n https://play.google.com/store/apps/details?id=com.cryptoxin \n Enter this Referral code \n ${walletData.address}"
                                                )
                                                type = "text/plain"
                                            }
                                            val shareIntent =
                                                Intent.createChooser(sendIntent , null)
                                            context.startActivity(shareIntent)
                                        }
                                        .size(28.dp)
                                        .padding(start = 8.dp) ,
                                    tint = chonolulublue
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_computer_24) ,
                                    contentDescription = null ,
                                    modifier = Modifier
                                        .clickable {
                                            val url = "https://cinscan.com/"
                                            uriHandler.openUri(
                                                Uri
                                                    .parse(url)
                                                    .toString()
                                            )
                                        }
                                        .size(28.dp)
                                        .padding(start = 8.dp) ,
                                    tint = chonolulublue
                                )
                            }
                        }

                        //middle

                        Row(
                            horizontalArrangement = Arrangement.Center ,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Image(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape) ,
                                painter = painterResource(id = R.drawable.cinlogosmall) ,
                                alignment = Alignment.CenterStart ,
                                contentDescription = "" ,
                                contentScale = ContentScale.Crop
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth() ,
                            horizontalArrangement = Arrangement.SpaceBetween ,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Text(
                                    text = "Available balance" ,
                                    style = TextStyle(color = if (darkTheme) cblack else cblack)
                                )
                                Text(
                                    text = "${
                                        String.format(
                                            "%.3f" ,
                                            CinBalanceData.msg.toDouble()
                                        )
                                    } CIN" ,
                                    style = TextStyle(color = if (darkTheme) cblack else cblack)
                                )
                            }
                            Column() {
                                Text(
                                    text = "Net Worth" ,
                                    style = TextStyle(color = if (darkTheme) cblack else cblack)
                                )
                                Text(
                                    text = "${
                                        String.format(
                                            "%.3f" ,
                                            (CinBalanceData.msg.toDouble() * 0.0001)
                                        )
                                    } CIN" ,
                                    style = TextStyle(color = if (darkTheme) cblack else cblack)
                                )
                            }
                            Column() {
                                Text(
                                    text = "Cin Price" ,
                                    style = TextStyle(color = if (darkTheme) cblack else cblack)
                                )
                                Text(
                                    text = "$0.0001" ,
                                    style = TextStyle(color = if (darkTheme) cblack else cblack)
                                )
                            }
                        }

                    }
                }


                //down

                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp) ,
                        horizontalArrangement = Arrangement.SpaceEvenly ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(48.dp)
                                .aspectRatio(1f)
                                .background(cwhite , shape = CircleShape)
                                .border(
                                    2.dp ,
                                    cyellow ,
                                    CircleShape
                                )
                                .clickable {
                                    navController.navigate(Screens.CinSendScreen.route)
                                } ,//add a boarder color if requires,
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cinbsend) ,
                                contentDescription = "image" ,
                                contentScale = ContentScale.Crop ,
                                modifier = Modifier
                                    .size(24.dp)
                                //
                            )

                        }

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(48.dp)
                                .aspectRatio(1f)
                                .background(cwhite , shape = CircleShape)
                                .border(
                                    2.dp ,
                                    cyellow ,
                                    CircleShape
                                )
                                .clickable {
                                    navController.navigate(Screens.CinReceiveScreen.route + "/${walletData.address}")
                                } ,//add a boarder color if requires,
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cinbreceive) ,
                                contentDescription = "image" ,
                                contentScale = ContentScale.Crop ,
                                modifier = Modifier
                                    .size(24.dp)
                            )

                        }

                    }
                }


            }


        }

        WalletTabLayout()
    }
}


//TabLayout()


@ExperimentalPagerApi
@Composable
fun WalletTabLayout() {

    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState(pageCount = 4)

    // on below line we are creating a column for our widgets.
    Column(

    ) {
        // on the below line we are specifying the top app bar
        // and specifying background color for it.

        // on below line we are calling tabs
        WalletTabs(pagerState = pagerState)
        // on below line we are calling tabs content
        // for displaying our page for each tab layout
        WalletTabsContent(pagerState = pagerState)
    }
}

// on below line we are
// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun WalletTabs(pagerState: PagerState) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf(
        "Transaction History" ,
        "Post Reward" ,
        "Like Reward" ,
        "Comment Reward" ,

        )
    // on below line we are creating
    // a variable for the scope.
    val scope = rememberCoroutineScope()
    // on below line we are creating a
    // individual row for our tab layout.
    TabRow(
        // on below line we are specifying
        // the selected index.
        selectedTabIndex = pagerState.currentPage ,

        // on below line we are
        // specifying background color.
        backgroundColor = chonolulublue ,

        // on below line we are specifying content color.
        contentColor = Color.White ,

        // on below line we are specifying
        // the indicator for the tab
        indicator = { tabPositions ->
            // on below line we are specifying the styling
            // for tab indicator by specifying height
            // and color for the tab indicator.
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState , tabPositions) ,
                height = 2.dp ,
                color = Color.White
            )
        }
    ) {
        // on below line we are specifying icon
        // and text for the individual tab item
        list.forEachIndexed { index , _ ->
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
                        list[index] ,
                        style = TextStyle(fontSize = 12.sp , fontWeight = FontWeight.Bold) ,
                        // on below line we are specifying the text color
                        // for the text in that tab
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray ,
                        maxLines = 2 ,
                        textAlign = TextAlign.Center
                    )
                } ,
                // on below line we are specifying
                // the tab which is selected.
                selected = pagerState.currentPage == index ,
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

// on below line we are creating a tab content method
// in which we will be displaying the individual page of our tab .

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WalletTabsContent(pagerState: PagerState) {
    // on below line we are creating
    // horizontal pager for our tab layout.
    HorizontalPager(state = pagerState) {
        // on below line we are specifying
        // the different pages.
            page ->
        when (page) {
            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            0 -> TransactionHistoryScreen()
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            1 -> PostRewardScreen()
            // on below line we are calling tab content screen
            // and specifying data as Settings Screen.
            2 -> LikeRewardScreen()

            3 -> CommentRewardScreen()
        }
    }
}


