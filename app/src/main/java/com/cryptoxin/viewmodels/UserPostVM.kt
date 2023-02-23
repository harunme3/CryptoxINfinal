package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.UserPostR
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.NoOfFollowerR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.NoOfFollowerS
import com.cryptoxin.uistate.UserPostS
import com.cryptoxin.uistate.WalletIdS
import com.cryptoxin.uistate.WalletS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPostVM @Inject constructor(private val userPostR: UserPostR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getUserPostStateFlow: MutableStateFlow<UserPostS> = MutableStateFlow(
        UserPostS.Empty)
    val _getUserPostStateFlow: StateFlow<UserPostS> = getUserPostStateFlow


    init {
        getUserPost()
    }

    private fun getUserPost() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getUserPostStateFlow.value = UserPostS.Loading
                userPostR.getUserPostR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getUserPostStateFlow.value = UserPostS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getUserPostStateFlow.value = UserPostS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}