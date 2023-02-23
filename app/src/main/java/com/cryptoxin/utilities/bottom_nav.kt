package com.cryptoxin.utilities
import androidx.compose.foundation.layout.size
import com.cryptoxin.bottombar.BottomNavItem
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cwhite


 @Composable
fun BottomNavigationBar(navController: NavHostController) {

        val listofitem= listOf<BottomNavItem>(
            BottomNavItem.Home,
            BottomNavItem.Follow,
            BottomNavItem.Tox,
            BottomNavItem.Wallet,
            BottomNavItem.Profile,

        )

        BottomNavigation(backgroundColor = chonolulublue,
        contentColor= cwhite
        ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        listofitem.forEach {

        BottomNavigationItem(
            selected = currentRoute==it.screen_route,
            icon ={
            Icon(painter =  painterResource(id = it.icon),
            contentDescription =it.title,
                modifier = Modifier.size(18.dp)
        )
        },
            selectedContentColor = cwhite,
            label = {
                Text(text = it.title,
                    style = TextStyle(
                        color = cwhite,
                        fontSize = 14.sp
                    ),

                )


                    },
            alwaysShowLabel = true,
            onClick = {
            navController.navigate(it.screen_route)

                    },
                    )
        }
        }

        }