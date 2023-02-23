package com.cryptoxin.screenui.cTopAppBar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoxin.R
import com.cryptoxin.screenui.profilescreen.EditProfileComponent
import com.cryptoxin.ui.theme.cgrayblue
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.credlight
import com.cryptoxin.ui.theme.cyellow
import com.cryptoxin.uistate.ProfileS
import com.cryptoxin.viewmodels.ProfileVM

@Composable
 fun DrawerContent(
    gradientColors: List<Color> = listOf(chonolulublue, cgrayblue) ,
    itemClick: (String) -> Unit,
    profileVM: ProfileVM = hiltViewModel()
) {
    val profileState = profileVM._getProfileStateFlow.collectAsState()
    Log.e("1111" , profileState.value.toString())

    val itemList = prepareNavigationDrawerItems()

    when (profileState.value) {
        is ProfileS.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize() ,
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp)) ,
                    color = cyellow

                )
            }
        }
        is ProfileS.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize() ,
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(64.dp)) ,
                    color = chonolulublue
                )
            }
        }
        is ProfileS.Error -> Text(text = "error")
        is ProfileS.Loaded -> {
            val profileStateData = (profileState.value as ProfileS.Loaded).data.data

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(colors = gradientColors)),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {

                item {


                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),) {


                        Image(
                            painter = painterResource(R.drawable.profiledata),
                            contentDescription = null,
                            modifier = Modifier
                                .size(75.dp)
                                .clip(CircleShape),
                            contentScale= ContentScale.Crop
                        )

                        // user's name
                        Text(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            text = profileStateData.Name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        // user's email
                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                            text = profileStateData.UserName,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }


                items(itemList) { item ->
                    NavigationListItem(item = item) {
                        itemClick(item.label)
                    }
                }
            }


        }


    }


}