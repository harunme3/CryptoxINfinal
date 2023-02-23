package com.cryptoxin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.ProfileUpdateBody
import com.cryptoxin.data.repository.ProfileUpdateR
import com.cryptoxin.uistate.ProfileUpdateS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileUpdateInitialVM @Inject constructor(
    private val profileUpdateR: ProfileUpdateR ,
) :
    ViewModel() {



    private val setProfileUpdateInitialStateFlow: MutableStateFlow<ProfileUpdateS> = MutableStateFlow(
        ProfileUpdateS.Empty
    )
    val _setProfileUpdateInitialStateFlow: StateFlow<ProfileUpdateS> = setProfileUpdateInitialStateFlow


    fun setProfileUpdate(
        myAddress: String ,
        privateKey: String ,
        name: String ,
        UserName: String ,
        email: String ,
        organization: String ,
        profileTag: String ,
        designation: String ,
        dob: String ,
        otherDetails: String ,
        Profileimgg: String ,
        backgroundimgg: String ,
    ) {
        viewModelScope.launch {
            val profileUpdateBody =
                ProfileUpdateBody(
                    myAddress = myAddress ,
                    privateKey = privateKey ,
                    Name = name ,
                    UserName = UserName ,
                    Organization = organization ,
                    designation = designation ,
                    Dob = dob ,
                    ProfileTag = profileTag ,
                    MailID = email ,
                    Otherdetail = otherDetails ,
                    Profileimgg = Profileimgg ,
                    backgroundimgg = backgroundimgg
                )
            Log.d("2222" , profileUpdateBody.toString())
            setProfileUpdateInitialStateFlow.value = ProfileUpdateS.Loading
            profileUpdateR.setProfileUpdateR(
                profileUpdateBody
            ).catch { e ->
                setProfileUpdateInitialStateFlow.value = ProfileUpdateS.Error(message = "Exception $e")
            }.collect { its ->
                if (its.data != null) {
                    val finalData = its.data
                    setProfileUpdateInitialStateFlow.value = ProfileUpdateS.Loaded(data = finalData)
                }
            }

        }
    }
}