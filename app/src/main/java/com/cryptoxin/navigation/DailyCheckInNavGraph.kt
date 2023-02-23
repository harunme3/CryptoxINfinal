package com.cryptoxin.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.cryptoxin.screenui.*
import com.cryptoxin.screenui.createImport.MandatoryDetails
import com.cryptoxin.screenui.createImport.ReferralScreen
import com.cryptoxin.screenui.dailycheckin.DailyCheckInDashboard


fun NavGraphBuilder.dailyCheckInNavGraph(navController: NavHostController){
    navigation(
        route = Graph.DAILY_CHECK_IN,
        startDestination = Screens.DailyCheckInDashboardScreen.route
    ){
        composable(Screens.DailyCheckInDashboardScreen.route){
            DailyCheckInDashboard(navController)
        }



    }
}