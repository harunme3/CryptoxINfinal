package com.cryptoxin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cryptoxin.screenui.ReferralPattern
import com.cryptoxin.screenui.referral.ReferralDashboard


fun NavGraphBuilder.referralNavGraph(navController: NavHostController){
    navigation(
        route = Graph.REFERRAL,
        startDestination = Screens.ReferralDashboardScreen.route
    ) {
        composable(Screens.ReferralDashboardScreen.route) {
            ReferralDashboard(navController)
        }

        composable(Screens.ReferralPatternScreen.route){
            ReferralPattern(navController)
        }

    }
}