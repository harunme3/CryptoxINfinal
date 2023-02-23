package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.NoOfFollowerR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.NoOfFollowerS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoOfFollowerVM @Inject constructor(private val noOfFollowerR: NoOfFollowerR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getNoOfFollowerStateFlow: MutableStateFlow<NoOfFollowerS> = MutableStateFlow(NoOfFollowerS.Empty)
    val _getNoOfFollowerStateFlow: StateFlow<NoOfFollowerS> = getNoOfFollowerStateFlow


    init {
        getNoOfFollower()
    }

    private fun getNoOfFollower() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getNoOfFollowerStateFlow.value = NoOfFollowerS.Loading
                noOfFollowerR.getNoOfFollowerR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getNoOfFollowerStateFlow.value = NoOfFollowerS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getNoOfFollowerStateFlow.value = NoOfFollowerS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}