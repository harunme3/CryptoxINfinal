package com.cryptoxin.screenui.cTopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.cryptoxin.R

@Composable
 fun prepareNavigationDrawerItems(): List<NavigationDrawerItem> {
    val itemsList = arrayListOf<NavigationDrawerItem>()

//    itemsList.add(
//        NavigationDrawerItem(
//            image = painterResource(id = R.drawable.home),
//            label = "Referral"
//        )
//    )
//    itemsList.add(
//        NavigationDrawerItem(
//            image = painterResource(id = R.drawable.comment),
//            label = "Rewards",
//            showUnreadBubble = true
//        )
//    )

//    itemsList.add(
//        NavigationDrawerItem(
//            image = painterResource(id = R.drawable.comment),
//            label = "AirDrop",
//            showUnreadBubble = true
//        )
//    )
//
//    itemsList.add(
//        NavigationDrawerItem(
//            image = painterResource(id = R.drawable.comment),
//            label = "About Us",
//            showUnreadBubble = true
//        )
//    )

    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.comment),
            label = "Messages",
            showUnreadBubble = true
        )
    )

    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.comment),
            label = "HubIN",
            showUnreadBubble = true
        )
    )

    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.comment),
            label = "Short Video",
            showUnreadBubble = true
        )
    )

    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.comment),
            label = "Video Call",
            showUnreadBubble = true
        )
    )

    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.comment),
            label = "Audio Call",
            showUnreadBubble = true
        )
    )



    return itemsList
}

data class NavigationDrawerItem(
    val image: Painter ,
    val label: String ,
    val showUnreadBubble: Boolean = false
)