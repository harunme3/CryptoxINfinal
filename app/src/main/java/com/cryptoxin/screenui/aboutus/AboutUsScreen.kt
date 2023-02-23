package com.cryptoxin.screenui.aboutus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cryptoxin.ui.theme.cwhite
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.RichTextStyle


@Composable
fun AboutUsScreen(navController: NavController) {

    val data ="# About us :\n" +
        "It is the world’s first social media platform based on WEB 3.0 and works on \n" +
            "a decentralized network. Every piece of information on the app is completely \n" +
            "secure and transparent. It is based on blockchain technology and equipped \n" +
            "with audio/video calls and HubIN broadcasting capabilities under one single platform. \n" +
            "The word “Cryptox” derived from two popular words i.e.; ‘‘Crypto+Stocks”. \n" +
            "And both the words are the economy-adjacent for every country in the world. \n" +
            "Whereas the word “IN” derived from the word “INDIA” \n" +
            ", which denotes the uniqueness \n" +
            "of the trade name with the country code \"IN\".\n" +
            "\n" +
            "Before CryptoxIN , social media companies have made billions using the data of users . Social media companies charge advertisers and show advertisements to users, in an ultimate game of commerce, users lose.\n" +
            "\n" +
            "Now no more,\n" +
            "Here is your solution with CryptoxIN, you are going to get paid for using and watching advertisements.\n" +
            "\n" +
            "CryptoxIN shares the revenue obtained from the advertisers in the form of CIN coins.\n" +
            "\n" +
            "Users get \n" +
            "1000 coins to refer friends\n" +
            "700 coins to post \n" +
            "150 coins to like \n" +
            "150 coins to post \n" +
            "150 coins to follow\n" +
            "150 coins to share.\n" +
            "\n" +
            "And this is not it \n" +
            "You obtain revenue with your referrals earning as well.\n" +
            "\n" +
            "\n" +
            "For more information download a small presentation \n" +
            "English \n" +
            "\n" +
            "Https://Fufi.info/cryptoxIN/English\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "Hindi \n" +
            "\n" +
            "Https://fufi.info/cryptoxin/Hindi\n" +
            "\n" +
            "\n" +
            "\n" +
            "Join us on Telegram \n" +
            "\n" +
            "https://t.me/crypto_X_IN"
    Column(modifier = Modifier.fillMaxSize().background(
        color = cwhite
    ),) {
        RichText(
            modifier = Modifier.padding(16.dp) ,
            style= RichTextStyle(

            )
            ) {
            Markdown(
                content = data ,
            )
        }
    }
}