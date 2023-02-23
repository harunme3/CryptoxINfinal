package com.cryptoxin.screenui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoxin.navigation.Screens
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.CreateWalletState
import com.cryptoxin.viewmodels.CreateWalletViewModels


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateAccount(navController: NavController, createWalletViewModels: CreateWalletViewModels= hiltViewModel()) {
    val state = createWalletViewModels._createWalletStateFlow.collectAsState()

    when (state.value) {
        is CreateWalletState.Empty -> {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp)),
                    color = cyellow
                )
            }
        }
        is CreateWalletState.Loading -> {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(64.dp)),
                    color = chonolulublue
                )
            }
        }
        is CreateWalletState.Error -> Text(text = "error")
        is CreateWalletState.Loaded -> {
            val context=LocalContext.current
            val clipboardManager: ClipboardManager = LocalClipboardManager.current
            val data=(state.value as CreateWalletState.Loaded).data.data
            val mnemonic:String=data.wallet.signingKey.mnemonic
            val privateKey:String=data.wallet.signingKey.keyPair.privateKey
            val address:String=data.wallet.signingKey.address
            val mnemonicList=mnemonic.split(" ").toList()


            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,)
            {
                Column( horizontalAlignment = Alignment.CenterHorizontally,){
                    Text(
                        text = "Your Secret Phrase",
                        style = TextStyle(
                            color = cblack,
                            fontSize = 24.sp

                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "Write down or copy these words in the right order and save them somewhere safe",
                        style = TextStyle(
                            color = cblack,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    Box(
                        modifier = Modifier
                            .background(color = cgraystronglight)
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        FlowRow(
                            modifier = Modifier.padding(4.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            maxItemsInEachRow = 4
                        ) {
                            mnemonicList.forEachIndexed  { index,it ->
                                Box(modifier = Modifier.padding(2.dp)) {
                                    Text(
                                        text = "${index + 1} $it",
                                        modifier = Modifier
                                            .background(
                                                color = cgraylight,
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .padding(8.dp),
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = "copy",
                        style = TextStyle(
                            color = chonolulublue,
                            fontSize = 24.sp
                        ),
                        modifier = Modifier.padding(vertical = 32.dp).clickable {
                            clipboardManager.setText(AnnotatedString((mnemonic)))
                            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                Column(){
                    Box(
                        modifier = Modifier
                            .background(color = Color.Red.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        Column() {
                            Text(
                                text = "DO NOT share your phrase to anyone as this gives full access to your wallet",
                                style = TextStyle(
                                    color = cdarkred,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )

                            Text(
                                text = "CryptoxIN support will NEVER reach out to ask for it",
                                style = TextStyle(
                                    color = cdarkred,
                                    fontSize = 14.sp

                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            navController.navigate(Screens.VerifyAccount.route+"/$mnemonic/$privateKey/$address")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = chonolulublue)
                    )
                    {
                        Text(
                            text = "Continue",
                            style = TextStyle(
                                color = cwhite,
                                fontSize = 18.sp

                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                    }
                }
            }
        }


    }
}


