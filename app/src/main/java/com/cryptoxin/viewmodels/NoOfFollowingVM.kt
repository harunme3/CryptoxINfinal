package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.NoOfFollowerR
import com.cryptoxin.data.repository.NoOfFollowingR
import com.cryptoxin.data.repository.ProfileR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.NoOfFollowerS
import com.cryptoxin.uistate.NoOfFollowingS
import com.cryptoxin.uistate.ProfileS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject





@HiltViewModel
class NoOfFollowingVM @Inject constructor(private val noOfFollowingR: NoOfFollowingR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getNoOfFollowingStateFlow: MutableStateFlow<NoOfFollowingS> = MutableStateFlow(
        NoOfFollowingS.Empty)
    val _getNoOfFollowingStateFlow: StateFlow<NoOfFollowingS> = getNoOfFollowingStateFlow


    init {
        getNoOfFollowing()
    }

    private fun getNoOfFollowing() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getNoOfFollowingStateFlow.value = NoOfFollowingS.Loading
                noOfFollowingR.getNoOfFollowingR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getNoOfFollowingStateFlow.value = NoOfFollowingS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getNoOfFollowingStateFlow.value = NoOfFollowingS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}