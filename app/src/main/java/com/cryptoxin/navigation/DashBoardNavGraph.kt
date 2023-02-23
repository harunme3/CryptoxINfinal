package com.cryptoxin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cryptoxin.screenui.FollowScreen
import com.cryptoxin.screenui.ReferralPattern

@Composable
fun DashBoardNavGraph(navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        route=Graph.DASHBOARD,
        startDestination = Graph.Home,
//        startDestination = Screens.ReferralPatternScreen.route,
    ){
        homeNavGraph(navController = navController)
        followNavGraph(navController = navController)
        toxNavGraph(navController = navController)
        walletNavGraph(navController = navController)
        profileNavGraph(navController = navController)
        //referral navGraph
        referralNavGraph(navController = navController)
        //daily check-in navGraph
        dailyCheckInNavGraph(navController = navController)




    }


}