package com.cryptoxin.screenui

import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.cryptoxin.R
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.WalletIdS
import com.cryptoxin.uistate.WalletS
import com.cryptoxin.viewmodels.ImageUpdateViewModel
import com.cryptoxin.viewmodels.WalletVM
import com.skydoves.balloon.*
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


@Composable
fun ToxScreen(imageUpdateViewModel: ImageUpdateViewModel = hiltViewModel() , walletVM: WalletVM = hiltViewModel() ,) {

    DoneScreen()
//    val walletState = walletVM._getAllWalletStateFlow.collectAsState()
//
//
//    val ctx = LocalContext.current
//
//    val builder = rememberBalloonBuilder {
//        setArrowSize(10)
//        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
//        setArrowPosition(0.5f)
//        setHeight(BalloonSizeSpec.WRAP)
//        setPadding(12)
//        setCornerRadius(8f)
//        setIsVisibleOverlay(true)
//        setDismissWhenClicked(true)
//    }
//
//    var multifiles : MutableList<MultipartBody.Part> = remember {
//        mutableStateListOf()
//    }
//    var inputext by remember {
//        mutableStateOf("")
//    }
//
//    var selectedImage by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//
//
//
//    val stateUpdateImage = imageUpdateViewModel._getUserStateFlow.collectAsState()
//
//    val launcher = rememberLauncherForActivityResult(contract =
//    ActivityResultContracts.GetContent()) { uri: Uri? ->
//        selectedImage = uri
//    }
//
//
//Column(modifier = Modifier
//    .fillMaxWidth()
//    .fillMaxHeight(0.93f)
//    .padding(vertical = 8.dp, horizontal = 8.dp),
//    verticalArrangement = Arrangement.SpaceBetween
//) {
//
//
//    //Create own text editor in kotlin
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        Text(text = stateUpdateImage.value.toString())
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .border(1.dp, cgraylight)
//        ) {
//            Row(modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp), verticalAlignment = Alignment.CenterVertically)
//            {
//
//
//                Image(
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(CircleShape),
//                    painter = painterResource(id = R.drawable.cinlogolarge),
//                    alignment = Alignment.CenterStart,
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Column() {
//                    Text(text ="CryptoxIN", style = TextStyle(color = chonolulublue, fontSize = 20.sp))
//                    Text(text = "@prvks", style = TextStyle(color = cgraystrong, fontSize = 14.sp))
//                }
//            }
//        }
//       TextField(
//            value = inputext,
//           placeholder = { Text(text = "What do you want to talk about")},
//           modifier = Modifier
//               .fillMaxWidth()
//               .border(
//                   BorderStroke(
//                       width = 0.dp,
//                       color = Color.Transparent
//                   ),
//               ),
//           colors = TextFieldDefaults.textFieldColors(
//               backgroundColor = Color.Transparent,
//               focusedIndicatorColor = Color.Transparent,
//               unfocusedIndicatorColor = Color.Transparent
//           ),
//           textStyle = TextStyle(fontSize = 18.sp),
//            onValueChange = {
//            inputext = it
//        })
//
//        //Image slider jetpack compose
//        Row(modifier = Modifier
//            .horizontalScroll(rememberScrollState())
//            .height(200.dp)
//            .fillMaxWidth()
//        ) {
//            if (selectedImage!=null) {
//                Box(modifier = Modifier.size(200.dp)) {
//                    Image(painter = rememberAsyncImagePainter(selectedImage),
//                        contentDescription = "Profile",
//                        contentScale = ContentScale.Crop
//                    )
//                }
//
//            }
//        }
//
//
//    }
//
//
//
//
//
//        Column {
//            Divider(modifier = Modifier.padding(bottom = 4.dp))
//            Row(modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//               verticalAlignment = Alignment.CenterVertically
//                ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.cimage),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(start = 8.dp)
//                        .clickable {
//                            launcher.launch("image/*")
//                        },
//                    tint = chonolulublue
//                )
//                Icon(
//                    painter = painterResource(id = R.drawable.cvideo),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(start = 8.dp),
//                    tint = chonolulublue
//                )
//
//
//                Balloon(builder = builder,
//                    balloonContent = {
//                       Row(modifier = Modifier.width(108.dp),
//                           horizontalArrangement = Arrangement.SpaceBetween
//                       ) {
//                           Text(
//                               modifier = Modifier.clickable {
//                                   inputext += "****"
//                               },
//                               text ="B", color = cwhite,
//                               style = TextStyle(fontWeight = FontWeight.Bold)
//                           )
//                           Text(
//                               modifier = Modifier.clickable {
//                                   inputext += "**"
//                               },
//                               text ="I", color = cwhite,
//                               style = TextStyle(fontStyle = FontStyle.Italic))
//                           Text(
//                               modifier = Modifier.clickable {
//                                   inputext += "<u></u>"
//                               },
//                               text ="U", color = cwhite,
//                               style = TextStyle(fontWeight = FontWeight.Bold,
//                                   textDecoration=TextDecoration.Underline
//                               )
//                           )
//
//                       }
//                    }
//                    ) {it->
//                    Icon(
//                        painter = painterResource(id = R.drawable.cfont),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .padding(start = 8.dp)
//                            .clickable {
//
//                                it.showAlignTop()
//
//                            },
//                        tint = chonolulublue
//                    )
//
//                }
//
//
//                Icon(
//                    painter = painterResource(id = R.drawable.cpoll),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(start = 8.dp)
//                        .clickable {
//
//                        },
//                    tint = chonolulublue
//                )
//
//
//                Icon(
//                    painter = painterResource(id = R.drawable.csend),
//                    contentDescription = null,
//                    tint = chonolulublue,
//                    modifier = Modifier
//                        .padding(start = 8.dp)
//                        .clickable {
//                            //ImageUpdate
//
//                            val path: File = Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_PICTURES
//                            )
//
//                            val name = getRandomString(16)
//                            Log.e("1111", name)
//                            val files = File(path, "$name.jpg")
//
//
//                            try {
//                                if (!path.isDirectory()) {
//                                    path.mkdirs()
//                                }
//                                files.createNewFile()
//                            } catch (e: Exception) {
//                                e.printStackTrace()
//                            }
//                            Log.e("2222", files.toString())
//                            files
//                                .outputStream()
//                                .use {
//                                    ctx.contentResolver
//                                        .openInputStream(selectedImage!!)
//                                        ?.copyTo(it)
//                                }
//
//                            Log.d("2222", files.toString())
//                            ///
//                            val requestFile = files.asRequestBody("image/*".toMediaTypeOrNull())
//                            val file =
//                                MultipartBody.Part.createFormData("file", files.name, requestFile)
//                            multifiles.add(file)
// if (walletState.value is WalletS.Loaded)
//     {
//            val data=(walletState.value as WalletS.Loaded).data
//            val myAddress =
//            data[0].address.toRequestBody("text/plain".toMediaTypeOrNull())
//            val privateKey =
//            data[0].privateKey.toRequestBody(
//            "text/plain".toMediaTypeOrNull()
//            )
//
//
//            val type =
//            "4".toRequestBody("text/plain".toMediaTypeOrNull())
//            val _hashtag =
//            "#cryptoxin".toRequestBody("text/plain".toMediaTypeOrNull())
//            val _content =
//            inputext.toRequestBody("text/plain".toMediaTypeOrNull())
//            val videoHash =
//            "".toRequestBody("text/plain".toMediaTypeOrNull())
//
//            imageUpdateViewModel.getImageUpdateCall(
//            multifiles,
//            myAddress,
//            privateKey,
//            type,
//            _content,
//            _hashtag,
//            videoHash
//            )
//    }
//  },
//                )
//
//
//            }
//
//
//        }
//
//
//}



}


fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}