package com.cryptoxin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cryptoxin.screenui.*

@Composable
fun RootNavigationGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route=Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){

        //create separate navigation graph for auth
        authNavGraph(navController = navController)

        //create a separate navigation graph for dashboard
        composable(route=Graph.DASHBOARD){
            DashBoardScreen()
        }


    }

}


object Graph{
    const val ROOT ="root_graph"
    const val AUTHENTICATION ="auth_graph"
    const val DASHBOARD ="dashboard_graph"
    const val Home ="home_graph"
    const val FOLLOW ="follow_graph"
    const val TOX ="tox_graph"
    const val WALLET ="wallet_graph"
    const val PROFILE ="profile_graph"
    const val REFERRAL ="referral_graph"
    const val DAILY_CHECK_IN ="daily_check_in_graph"
}