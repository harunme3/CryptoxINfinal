package com.cryptoxin.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.cryptoxin.appversion.AppVersionScreen
import com.cryptoxin.screenui.*
import com.cryptoxin.screenui.createImport.MandatoryDetails
import com.cryptoxin.screenui.createImport.ReferralScreen
import com.cryptoxin.screenui.otpless.OtpLessScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screens.SplashScreen.route
    ){
        composable(Screens.SplashScreen.route){
            SplashScreen(navController)
        }

        composable(Screens.OtpScreen.route){
            OtpLessScreen(navController)
        }


        composable(Screens.AppVersionScreen.route){
            BackHandler(true) {
                // Or do nothing
                Log.d("1111","Can't back")

            }
            AppVersionScreen()
        }

        composable(Screens.CreateImport.route){
            CreateImport(navController)
        }

        composable(Screens.CreateAccount.route){
            BackHandler(true) {
                // Or do nothing
                Log.d("1111","Can't back")

            }
            CreateAccount(navController)
        }

        composable(Screens.ImportAccount.route){
            BackHandler(true) {
                // Or do nothing
                Log.d("1111","Can't back")

            }
            ImportAccount(navController)
        }


        composable(Screens.MandatoryDetails.route+"/{mnemonic}/{privateKey}/{address}/{referralCode}",arguments = listOf(
            navArgument("mnemonic")  { type = NavType.StringType },
            navArgument("privateKey")  { type = NavType.StringType },
            navArgument("address")  { type = NavType.StringType },
            navArgument("referralCode")  { type = NavType.StringType },
        )
        )
        {
            val mnemonic=it.arguments?.getString("mnemonic")
            val privateKey=it.arguments?.getString("privateKey")
            val address=it.arguments?.getString("address")
            val referralCode=it.arguments?.getString("referralCode")
            BackHandler(true) {
                // Or do nothing
                Log.d("1111","Can't back")

            }
            MandatoryDetails(navController, mnemonic = mnemonic!!, privateKey = privateKey!!, address = address!!, referralCode = referralCode!!)
        }




        composable(Screens.ReferralScreen.route+"/{mnemonic}/{privateKey}/{address}",arguments = listOf(
            navArgument("mnemonic")  { type = NavType.StringType },
            navArgument("privateKey")  { type = NavType.StringType },
            navArgument("address")  { type = NavType.StringType },
        )
        )
        {
            val mnemonic=it.arguments?.getString("mnemonic")
            val privateKey=it.arguments?.getString("privateKey")
            val address=it.arguments?.getString("address")
            BackHandler(true) {
                // Or do nothing
                Log.d("1111","Can't back")

            }
            ReferralScreen(navController, mnemonic = mnemonic!!, privateKey = privateKey!!, address = address!!)
        }


        composable(Screens.VerifyAccount.route+"/{mnemonic}/{privateKey}/{address}",arguments = listOf(
            navArgument("mnemonic")  { type = NavType.StringType },
            navArgument("privateKey")  { type = NavType.StringType },
            navArgument("address")  { type = NavType.StringType },
        )
        )
        {
            val mnemonic=it.arguments?.getString("mnemonic")
            val privateKey=it.arguments?.getString("privateKey")
            val address=it.arguments?.getString("address")
            BackHandler(true) {
                // Or do nothing
                Log.d("1111","Can't back")

            }
            VerifyAccount(navController, mnemonic = mnemonic!!, privateKey = privateKey!!, address = address!!)
        }

    }
}