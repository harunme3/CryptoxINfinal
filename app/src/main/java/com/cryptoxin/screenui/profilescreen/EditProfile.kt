package com.cryptoxin.screenui.profilescreen

import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cryptoxin.R
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.models.profilem.ProfileM
import com.cryptoxin.screenui.getRandomString
import com.cryptoxin.ui.theme.*
import com.cryptoxin.uistate.ImageUpdateState
import com.cryptoxin.uistate.ProfileS
import com.cryptoxin.uistate.ProfileUpdateS
import com.cryptoxin.uistate.WalletS
import com.cryptoxin.viewmodels.ImageUpdateViewModel
import com.cryptoxin.viewmodels.ProfileUpdateVM
import com.cryptoxin.viewmodels.ProfileVM
import com.cryptoxin.viewmodels.WalletVM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


@Composable
fun EditProfile(navController: NavController ,
                profileVM: ProfileVM = hiltViewModel(),
                walletVM: WalletVM = hiltViewModel()
) {

    val profileState = profileVM._getProfileStateFlow.collectAsState()
    val walletState = walletVM._getAllWalletStateFlow.collectAsState()
    Log.e("1111" , profileState.value.toString())



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
            when (walletState.value) {
                is WalletS.Empty -> {
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
                is WalletS.Loading -> {
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
                is WalletS.Error -> Text(text = "error")
                is WalletS.Loaded -> {
                    val profileStateData = (profileState.value as ProfileS.Loaded).data
                    val WalletIdSData = (walletState.value as WalletS.Loaded).data[0]
                    EditProfileComponent(
                        navController ,
                        profileStateData ,
                        WalletIdSData
                    )
                }

            }

        }

    }


}


@Composable
fun EditProfileComponent(
    navController: NavController ,
    data: ProfileM ,
    WalletIdSData:WalletEntity ,
    profileUpdateVM: ProfileUpdateVM = hiltViewModel()
) {

    val context = LocalContext.current
    val darkTheme: Boolean = isSystemInDarkTheme()
    val profileUpdateVMState = profileUpdateVM._setProfileUpdateStateFlow.collectAsState()


    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    var multifiles: MutableList<MultipartBody.Part> = remember {
        mutableStateListOf()
    }
    var inputext by remember {
        mutableStateOf("")
    }


    //===================*************+++++++++++++++++++++++
    //data
    var name by remember { mutableStateOf(TextFieldValue(data.data.Name)) }
    var email by remember { mutableStateOf(TextFieldValue(data.data.MailID)) }
    var organization by remember { mutableStateOf(TextFieldValue(data.data.Organization)) }
    var profileTag by remember { mutableStateOf(TextFieldValue(data.data.ProfileTag)) }
    var designation by remember { mutableStateOf(TextFieldValue(data.data.designation)) }
    var dob by remember { mutableStateOf(TextFieldValue(data.data.Dobss)) }
    var otherDetails by remember { mutableStateOf(TextFieldValue(data.data.Otherdetail)) }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImage = uri
    }


    var savetxt by remember {
        mutableStateOf("Save")
    }


        if (profileUpdateVMState.value is ProfileUpdateS.Loaded) {
            LaunchedEffect(key1 = "key2") {
                Toast.makeText(context , "Updated Successfully" , Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
















    Column(
        horizontalAlignment = Alignment.CenterHorizontally ,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 16.dp) ,
    ) {

        Box(modifier = Modifier.fillMaxHeight(0.05f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically ,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(text = "Edit Profile" , fontSize = 22.sp)
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ) ,
                    contentPadding = PaddingValues() ,
                    onClick = {

                        //========================Profile update==========================//

                        savetxt = "Saving"
                        profileUpdateVM.setProfileUpdate(
                            name.text ,
                            data.data.UserName ,
                            email.text ,
                            organization.text ,
                            profileTag.text ,
                            designation.text ,
                            dob.text ,
                            otherDetails.text ,
                            data.data.Profileimgg ,
                            data.data.backgroundimgg
                        )







                        //========================profile details==========================//


                    }
                )
                {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFF0e4869) ,
                                        Color(0xFF175c87) ,
                                        Color(0xFF2470a6) ,
                                        Color(0xFF3385c6) ,
                                        Color(0xFF459ae7) ,
                                    ) ,
                                ) ,
                            )
                            .padding(horizontal = 18.dp , vertical = 8.dp) ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = savetxt , color = Color.White)
                    }
                }

            }

        }
        ConstraintLayout(
        ) {

            val camera = createRef()


            Box(modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable {
                    launcher.launch("image/*")
                }
            ) {
                Image(
                    painter = if (data.data.Profileimgg == "") painterResource(R.drawable.profiledata) else rememberAsyncImagePainter(
                        model = data.data.Profileimgg
                    ) ,
                    contentDescription = "Profile" ,
                    contentScale = ContentScale.Crop ,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
            Box(modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(color = cgraystronglight)
                .constrainAs(camera) {
                    end.linkTo(camera.end)
                }) {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_edit_24) ,
                    contentDescription = "Camera" ,
                    contentScale = ContentScale.FillWidth ,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clip(CircleShape)
                )
            }


        }


        Column() {
            Box(modifier = Modifier.height(50.dp))
            Column() {

                Text(text = "Name" , color = cgraystronglight)
                BasicTextField(
                    value = name ,
                    onValueChange = {
                        name = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    )
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }
        Column() {
            Box(modifier = Modifier.height(10.dp))
            Column() {
                Text(text = "Email" , color = cgraylight)
                BasicTextField(
                    value = email ,
                    onValueChange = {
                        email = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    ) ,
                    cursorBrush = if (darkTheme) Brush.linearGradient(
                        colors = listOf(
                            cwhite ,
                            cwhite
                        )
                    ) else Brush.linearGradient(colors = listOf(cwhite , cwhite)) ,
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }
        Column() {
            Box(modifier = Modifier.height(10.dp))
            Column() {

                Text(text = "Organization" , color = cgraylight)
                BasicTextField(
                    value = organization ,
                    onValueChange = {
                        organization = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    ) ,
                    cursorBrush = if (darkTheme) Brush.linearGradient(
                        colors = listOf(
                            cwhite ,
                            cwhite
                        )
                    ) else Brush.linearGradient(colors = listOf(cwhite , cwhite)) ,

                    )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }
        Column() {
            Box(modifier = Modifier.height(10.dp))
            Column() {
                Text(text = "Profile #Tag" , color = cgraystronglight)
                BasicTextField(
                    value = profileTag ,
                    onValueChange = {
                        profileTag = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    ) ,
                    cursorBrush = if (darkTheme) Brush.linearGradient(
                        colors = listOf(
                            cwhite ,
                            cwhite
                        )
                    ) else Brush.linearGradient(colors = listOf(cwhite , cwhite)) ,
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }
        Column() {
            Box(modifier = Modifier.height(10.dp))
            Column() {
                Text(text = "Designation" , color = cgraystronglight)
                BasicTextField(
                    value = designation ,
                    onValueChange = {
                        designation = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    ) ,
                    cursorBrush = if (darkTheme) Brush.linearGradient(
                        colors = listOf(
                            cwhite ,
                            cwhite
                        )
                    ) else Brush.linearGradient(colors = listOf(cwhite , cwhite)) ,
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }

        Column() {
            Box(modifier = Modifier.height(10.dp))
            Column() {
                Text(text = "Date of Birth" , color = cgraystronglight)
                BasicTextField(
                    value = dob ,
                    onValueChange = {
                        dob = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    ) ,
                    cursorBrush = if (darkTheme) Brush.linearGradient(
                        colors = listOf(
                            cwhite ,
                            cwhite
                        )
                    ) else Brush.linearGradient(colors = listOf(cwhite , cwhite)) ,
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }
        Column() {
            Box(modifier = Modifier.height(10.dp))
            Column() {
                Text(text = "Other details" , color = cgraylight)
                BasicTextField(
                    value = otherDetails ,
                    onValueChange = {
                        otherDetails = it
                    } ,
                    textStyle = TextStyle(
                        fontSize = 16.sp ,
                        color = if (darkTheme) cwhite else cblack ,
                    ) ,
                    cursorBrush = if (darkTheme) Brush.linearGradient(
                        colors = listOf(
                            cwhite ,
                            cwhite
                        )
                    ) else Brush.linearGradient(colors = listOf(cwhite , cwhite)) ,
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp , vertical = 8.dp))
        }
    }
}







