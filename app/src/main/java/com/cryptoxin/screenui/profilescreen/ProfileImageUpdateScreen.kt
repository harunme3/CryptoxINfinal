package com.cryptoxin.screenui.profilescreen

import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.cryptoxin.ui.theme.cgraystronglight
import com.cryptoxin.ui.theme.chonolulublue
import com.cryptoxin.ui.theme.cyellow
import com.cryptoxin.uistate.ProfileS
import com.cryptoxin.uistate.WalletS
import com.cryptoxin.viewmodels.ImageUpdateViewModel
import com.cryptoxin.viewmodels.ProfileVM
import com.cryptoxin.viewmodels.WalletVM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


@Composable
fun ProfileImageUpdateScreen(
    navController: NavController ,
    profileVM: ProfileVM = hiltViewModel() ,
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
                    val walletIdData = (walletState.value as WalletS.Loaded).data[0]
                    ProfileImageUpdateScreenComponent(
                        navController ,
                        profileStateData ,
                        walletIdData
                    )
                }

            }

        }

    }


}


@Composable
fun ProfileImageUpdateScreenComponent(
    navController: NavController ,
    profileStateData: ProfileM ,
    WalletIdSData: WalletEntity ,
    imageUpdateViewModel: ImageUpdateViewModel = hiltViewModel() ,
) {


    val ctx = LocalContext.current
    val stateUpdateImage = imageUpdateViewModel._getUserStateFlow.collectAsState()
    Log.e("33333" , stateUpdateImage.value.toString())


    var multifiles: MutableList<MultipartBody.Part> = remember {
        mutableStateListOf()
    }
    var inputext by remember {
        mutableStateOf("")
    }

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }


    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImage = uri
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally ,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp) ,
    ) {


        Box(modifier = Modifier.fillMaxHeight(0.05f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically ,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(text = "Update Profile" , fontSize = 22.sp)
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ) ,
                    contentPadding = PaddingValues() ,
                    onClick = {
                        val path: File = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES
                        )

                        val name = getRandomString(16)
                        Log.e("1111" , name)
                        val files = File(path , "$name.jpg")


                        try {
                            if (!path.isDirectory()) {
                                path.mkdirs()
                            }
                            files.createNewFile()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        Log.e("2222" , files.toString())
                        files
                            .outputStream()
                            .use {
                                ctx.contentResolver
                                    .openInputStream(selectedImage!!)
                                    ?.copyTo(it)
                            }

                        Log.d("2222" , files.toString())
                        ///
                        val requestFile = files.asRequestBody("image/*".toMediaTypeOrNull())
                        val file =
                            MultipartBody.Part.createFormData("file" , files.name , requestFile)
                        multifiles.add(file)
                        //---------------

                        val myAddress =
                            WalletIdSData.address.toRequestBody("text/plain".toMediaTypeOrNull())
                        val privateKey =
                            WalletIdSData.privateKey.toRequestBody(
                                "text/plain".toMediaTypeOrNull()
                            )

                        val type =
                            "1".toRequestBody("text/plain".toMediaTypeOrNull())
                        val _hashtag =
                            "#cryptoxin".toRequestBody("text/plain".toMediaTypeOrNull())
                        val _content =
                            "".toRequestBody("text/plain".toMediaTypeOrNull())
                        val videoHash =
                            "".toRequestBody("text/plain".toMediaTypeOrNull())

                        imageUpdateViewModel.getImageUpdateCall(
                            multifiles ,
                            myAddress ,
                            privateKey ,
                            type ,
                            _content ,
                            _hashtag ,
                            videoHash
                        )


                    } ,
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
                        Text(text = "Save" , color = Color.White)
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
                if (selectedImage != null) {
                    Log.d("1111" , selectedImage.toString())

                    Image(
                        painter = rememberAsyncImagePainter(selectedImage) ,
                        contentDescription = "Profile" ,
                        contentScale = ContentScale.Crop ,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.profile) ,
                        contentDescription = "Profile" ,
                        contentScale = ContentScale.Crop ,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
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

    }
}


