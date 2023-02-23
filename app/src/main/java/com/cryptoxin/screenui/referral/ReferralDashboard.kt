package com.cryptoxin.screenui.referral

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.models.bonusrewardm.BonusRewardM
import com.cryptoxin.data.models.directreferralcountm.DirectReferralCountM
import com.cryptoxin.data.models.fiftylevelrewardm.FiftyLevelRewardM
import com.cryptoxin.data.models.referralhistory.ReferralHistoryM
import com.cryptoxin.data.models.referralrewardm.ReferralRewardM
import com.cryptoxin.data.models.totalreferralcountm.TotalReferralCountM
import com.cryptoxin.screenui.walletui.CommentRewardScreen
import com.cryptoxin.screenui.walletui.LikeRewardScreen
import com.cryptoxin.screenui.walletui.PostRewardScreen
import com.cryptoxin.screenui.walletui.TransactionHistoryScreen
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.ui.theme.cyellow
import com.cryptoxin.uistate.*
import com.cryptoxin.viewmodels.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@Composable
fun ReferralDashboard(
    navController: NavController ,
    referralRewardVM: ReferralRewardVM = hiltViewModel() ,
    totalReferralCountVM: TotalReferralCountVM = hiltViewModel() ,
    directReferralCountVM: DirectReferralCountVM = hiltViewModel() ,
    fiftyLevelRewardVM: FiftyLevelRewardVM = hiltViewModel() ,
    referralHistoryVM: ReferralHistoryVM = hiltViewModel(),
    walletVM: WalletVM= hiltViewModel()


) {
    LaunchedEffect(Unit) {
        walletVM.getWallet(walletId = 1)
        referralRewardVM.getReferralReward()
        totalReferralCountVM.getTotalReferralCount()
        directReferralCountVM.getDirectReferralCount()
    }
    val walletState =
        walletVM._getWalletStateFlow.collectAsState()
    val fiftyLevelRewardState = fiftyLevelRewardVM._getFiftyLevelRewardStateFlow.collectAsState()
    val referralHistoryState = referralHistoryVM._getReferralHistoryStateFlow.collectAsState()
    val referralRewardState = referralRewardVM._getReferralRewardStateFlow.collectAsState()
    val totalReferralCountState =
        totalReferralCountVM._getTotalReferralCountStateFlow.collectAsState()
    val directReferralCountState =
        directReferralCountVM._getDirectReferralCountStateFlow.collectAsState()
    when (referralRewardState.value) {
        is ReferralRewardS.Empty -> {
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
        is ReferralRewardS.Loading -> {
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
        is ReferralRewardS.Error -> Text(text = "error")
        is ReferralRewardS.Loaded -> {
            when (totalReferralCountState.value) {
                is TotalReferralCountS.Empty -> {
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
                is TotalReferralCountS.Loading -> {
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
                is TotalReferralCountS.Error -> Text(text = "error")
                is TotalReferralCountS.Loaded -> {


                    when (directReferralCountState.value) {
                        is DirectReferralCountS.Empty -> {
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
                        is DirectReferralCountS.Loading -> {
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
                        is DirectReferralCountS.Error -> Text(text = "error")
                        is DirectReferralCountS.Loaded -> {


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


                                    when (referralHistoryState.value) {
                                        is ReferralHistoryS.Empty -> {
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
                                        is ReferralHistoryS.Loading -> {
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
                                        is ReferralHistoryS.Error -> Text(text = "error")
                                        is ReferralHistoryS.Loaded -> {


                                            val referralRewardData =
                                                (referralRewardState.value as ReferralRewardS.Loaded).data
                                            val totalReferralCountData =
                                                (totalReferralCountState.value as TotalReferralCountS.Loaded).data
                                            val directReferralCountData =
                                                (directReferralCountState.value as DirectReferralCountS.Loaded).data
                                            val fiftyLevelRewardData =
                                                (fiftyLevelRewardState.value as FiftyLevelRewardS.Loaded).data
                                            val referralHistoryData =
                                                (referralHistoryState.value as ReferralHistoryS.Loaded).data
                                            val walletData =
                                                (walletState.value as WalletIdS.Loaded).data
                                            ReferralDashboardComponent(
                                                referralRewardData ,
                                                totalReferralCountData ,
                                                directReferralCountData,
                                                fiftyLevelRewardData,
                                                referralHistoryData,
                                                walletData
                                            )


                                        }


                                    }

                                }


                            }


                        }


                    }

                }


            }

        }


    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReferralDashboardComponent(
    referralRewardData: ReferralRewardM ,
    totalReferralCountData: TotalReferralCountM ,
    directReferralCountData: DirectReferralCountM ,
    fiftyLevelRewardData: FiftyLevelRewardM ,
    referralHistoryData: ReferralHistoryM ,
    walletData: WalletEntity ,

    ) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
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
                        .height(240.dp)
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

                        Column(
                            modifier = Modifier.fillMaxWidth() ,
                            verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {

                            Text(
                                text = "Your Referral Code" ,
                                style = TextStyle(fontWeight = FontWeight.Bold , fontSize = 16.sp)
                            )
                            Text(text = walletData.address,    style = TextStyle(fontSize = 12.sp))
                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "Total  Affiliate Reward" ,
                                style = TextStyle(fontWeight = FontWeight.Bold , fontSize = 16.sp)
                            )
                            Text(text = referralRewardData.data)
                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "Total Affiliate Count" ,
                                style = TextStyle(fontWeight = FontWeight.Bold , fontSize = 16.sp)
                            )
                            Text(text = totalReferralCountData.data)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Total Direct Reward" ,
                                style = TextStyle(fontWeight = FontWeight.Bold , fontSize = 16.sp)
                            )
                            Text(text = directReferralCountData.data)

                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Total CIN Holding Reward" ,
                                style = TextStyle(fontWeight = FontWeight.Bold , fontSize = 16.sp)
                            )
                            Text(text = directReferralCountData.data)


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
                        horizontalArrangement = Arrangement.Center ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        OutlinedButton(onClick = { /*TODO*/ }) {
                            Text("Affiliate Pattern")
                        }
                    }
                }
            }


        }

        ReferralDashboardTabLayout()
    }
}


//TabLayout()


@ExperimentalPagerApi
@Composable
fun ReferralDashboardTabLayout() {

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
        ReferralDashboardTabs(pagerState = pagerState)
        // on below line we are calling tabs content
        // for displaying our page for each tab layout
        ReferralDashboardContent(pagerState = pagerState)
    }
}

// on below line we are
// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun ReferralDashboardTabs(pagerState: PagerState) {
    // in this function we are creating a list
    // in this list we are specifying data as
    // name of the tab and the icon for it.
    val list = listOf(
        "Direct Reward" ,
        "Affiliate Reward" ,
        "Holding Reward" ,

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
fun ReferralDashboardContent(pagerState: PagerState) {
    // on below line we are creating
    // horizontal pager for our tab layout.
    HorizontalPager(state = pagerState) {
        // on below line we are specifying
        // the different pages.
            page ->
        when (page) {
            // on below line we are calling tab content screen
            // and specifying data as Home Screen.
            0 -> DirectRewardScreen()
            // on below line we are calling tab content screen
            // and specifying data as Shopping Screen.
            1 -> AffiliateRewardScreen()
            // on below line we are calling tab content screen
            // and specifying data as Settings Screen.
            2 -> HoldingRewardScreen()

        }
    }
}


