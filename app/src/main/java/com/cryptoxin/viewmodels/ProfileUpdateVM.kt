package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.ProfileUpdateBody
import com.cryptoxin.data.repository.ProfileUpdateR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.ProfileUpdateS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileUpdateVM @Inject constructor(private val profileUpdateR: ProfileUpdateR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val setProfileUpdateStateFlow: MutableStateFlow<ProfileUpdateS> = MutableStateFlow(
        ProfileUpdateS.Empty)
    val _setProfileUpdateStateFlow: StateFlow<ProfileUpdateS> = setProfileUpdateStateFlow




     fun setProfileUpdate(
         name: String ,
         UserName: String ,
         email: String ,
         organization: String ,
         profileTag: String ,
         designation: String ,
         dob: String ,
         otherDetails: String,
         Profileimgg : String,
         backgroundimgg : String,
     ) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
             val  profileUpdateBody =
                    ProfileUpdateBody(
                        myAddress = it.address ,
                        privateKey = it.privateKey ,
                        Name = name,
                        UserName = UserName ,
                        Organization =organization ,
                        designation =designation ,
                        Dob =dob ,
                        ProfileTag = profileTag ,
                        MailID = email,
                        Otherdetail = otherDetails ,
                        Profileimgg = Profileimgg ,
                        backgroundimgg = backgroundimgg
                    )
                Log.d("2222",profileUpdateBody.toString())
                setProfileUpdateStateFlow.value = ProfileUpdateS.Loading
                profileUpdateR.setProfileUpdateR(
                    profileUpdateBody
                ).catch { e ->
                    setProfileUpdateStateFlow.value = ProfileUpdateS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        setProfileUpdateStateFlow.value = ProfileUpdateS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}