package com.cryptoxin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cryptoxin.screenui.ProfileScreen
import com.cryptoxin.screenui.aboutus.AboutUsScreen
import com.cryptoxin.screenui.airdrop.AirDropScreen
import com.cryptoxin.screenui.profilescreen.EditProfile
import com.cryptoxin.screenui.profilescreen.ProfileImageUpdateScreen


fun NavGraphBuilder.profileNavGraph(navController: NavHostController){
    navigation(
        route = Graph.PROFILE,
        startDestination = Screens.Profile.route
    ){

        composable(Screens.Profile.route){
            ProfileScreen(navController)
        }

        composable(Screens.EditProfile.route){
            EditProfile(navController)
        }

        composable(Screens.ProfileImageUpdateScreen.route){
           ProfileImageUpdateScreen(navController)
        }

        composable(Screens.AboutUsScreen.route){
            AboutUsScreen(navController)
        }

        composable(Screens.AirDropScreen.route){
            AirDropScreen(navController)
        }

    }
}