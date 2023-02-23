package com.cryptoxin.bottombar

import com.cryptoxin.R
import com.cryptoxin.navigation.Screens

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.home, Screens.Home.route)
    object Follow: BottomNavItem("Follow", R.drawable.follow, Screens.Follow.route)
    object Tox: BottomNavItem("Tox", R.drawable.tox, Screens.Tox.route)
    object Wallet: BottomNavItem("Wallet", R.drawable.wallet, Screens.Wallet.route)
    object Profile: BottomNavItem("Profile", R.drawable.profile, Screens.Profile.route)


}
