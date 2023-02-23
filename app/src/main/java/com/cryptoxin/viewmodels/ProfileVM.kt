package com.cryptoxin.viewmodels


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.ProfileR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.ProfileS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileVM @Inject constructor(private val profileR: ProfileR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getProfileStateFlow: MutableStateFlow<ProfileS> = MutableStateFlow(ProfileS.Empty)
    val _getProfileStateFlow: StateFlow<ProfileS> = getProfileStateFlow


    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getProfileStateFlow.value = ProfileS.Loading
                profileR.getProfileR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getProfileStateFlow.value = ProfileS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val getAllPostData = it.data
                        getProfileStateFlow.value = ProfileS.Loaded(data = getAllPostData)
                    }
                }

            }
        }

    }

}