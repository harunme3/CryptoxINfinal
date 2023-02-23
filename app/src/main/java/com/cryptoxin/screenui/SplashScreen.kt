package com.cryptoxin.screenui


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cryptoxin.BuildConfig
import com.cryptoxin.navigation.Graph
import com.cryptoxin.navigation.Screens
import com.cryptoxin.ui.theme.RainbowColors
import com.cryptoxin.ui.theme.cwhite
import com.cryptoxin.uistate.AppVersionS
import com.cryptoxin.uistate.WalletS
import com.cryptoxin.viewmodels.AppVersionVM
import com.cryptoxin.viewmodels.MultiWalletVM

@OptIn(ExperimentalTextApi::class)
@Composable
fun SplashScreen(
    navController: NavHostController ,
    multiWalletVM: MultiWalletVM = hiltViewModel() ,
    appVersionVM: AppVersionVM = hiltViewModel()
) {
    val multiWalletState = multiWalletVM._getAllWalletStateFlow.collectAsState()
    val appVersionState = appVersionVM._getAppVersionStateFlow.collectAsState()


    if (appVersionState.value is AppVersionS.Loaded) {
        LaunchedEffect(key1 = "key8") {
            val playStoreAppVersionData =
                (appVersionState.value as AppVersionS.Loaded).data.version.toInt()
            val mobileAppVersion = BuildConfig.VERSION_NAME.substring(4 , 7).toInt()
            if (mobileAppVersion < playStoreAppVersionData) {
                navController.navigate(Screens.AppVersionScreen.route)
            } else {
                Log.d(
                    "9999" ,
                    "$mobileAppVersion  $playStoreAppVersionData wallet send to splash screen"
                )
                multiWalletVM.getAllWallet()
            }
        }
    }

    if (multiWalletState.value is WalletS.Loaded) {

        navController.popBackStack()
                navController.navigate(Screens.OtpScreen.route)

//        LaunchedEffect(key1 = true) {
//            if ((multiWalletState.value as WalletS.Loaded).data.isEmpty()) {
//                navController.popBackStack()
//                navController.navigate(Screens.CreateImport.route)
//            } else {
//                navController.popBackStack()
//                navController.navigate(Graph.DASHBOARD)
//
//            }
//        }
    }



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(com.cryptoxin.R.drawable.cbackground) ,
            contentDescription = "CBackground" ,
            modifier = Modifier
                .fillMaxSize() ,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = com.cryptoxin.R.drawable.clightlogo) ,
                contentDescription = "CLogo" ,
            )
            Box(modifier = Modifier.height(40.dp))
            Text(
                text = "Welcome to Web 3.0 based\n Social Media Platform" ,
                style = TextStyle(
                    color = cwhite ,
                    fontSize = 14.sp
                ) ,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Proudly made in India!" ,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = RainbowColors ,
                        tileMode = TileMode.Mirror
                    ) ,
                    fontSize = 25.sp
                )
            )
        }


    }


}


