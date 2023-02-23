package com.cryptoxin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cryptoxin.screenui.FollowScreen
import com.cryptoxin.screenui.airdrop.AirDropScreen
import com.cryptoxin.screenui.profilescreen.FollowerScreen
import com.cryptoxin.screenui.profilescreen.FollowingScreen


fun NavGraphBuilder.followNavGraph(navController: NavHostController){
    navigation(
        route = Graph.FOLLOW,
        startDestination = Screens.Follow.route
    ){
        composable(Screens.Follow.route){
            FollowScreen(navController)
        }

        composable(Screens.AirDropScreen.route){
            AirDropScreen(navController)
        }

        composable(Screens.FollowerScreen.route){
            FollowerScreen(navController)
        }


        composable(Screens.FollowingScreen.route){
            FollowingScreen(navController)
        }

    }
}