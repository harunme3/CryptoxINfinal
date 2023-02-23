package com.cryptoxin.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.cryptoxin.screenui.HomeScreen
import com.cryptoxin.screenui.createImport.MandatoryDetails
import com.cryptoxin.screenui.homeui.CreateCommentScreen
import com.cryptoxin.screenui.homeui.ViewPostDetailsScreen
import com.cryptoxin.screenui.homeui.ViewPostScreen
import com.cryptoxin.screenui.referral.ReferralTreeDataScreen


fun NavGraphBuilder.homeNavGraph(navController: NavHostController){
   navigation(
       route = Graph.Home,
       startDestination = Screens.Home.route
   ){
       composable(Screens.Home.route){
           HomeScreen(navController)
       }




       composable(Screens.CreateCommentScreen.route+"/{postId}",arguments = listOf(
           navArgument("postId")  { type = NavType.StringType } ,
       )
       )
       {
           val postId=it.arguments?.getString("postId")

           CreateCommentScreen(navController, postId = postId!!)
       }




       composable(Screens.ViewPostScreen.route+"/{postId}",arguments = listOf(
           navArgument("postId")  { type = NavType.StringType } ,
       )
       )
       {
           val postId=it.arguments?.getString("postId")

           ViewPostScreen(navController, postId = postId!!)
       }


       composable(Screens.ViewPostDetailsScreen.route){
           ViewPostDetailsScreen(navController)
       }

       composable(Screens.ReferralTreeDataScreen.route){
           ReferralTreeDataScreen(navController)
       }


   }
}