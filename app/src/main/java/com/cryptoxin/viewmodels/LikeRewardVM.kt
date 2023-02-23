package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.LikeRewardR
import com.cryptoxin.uistate.LikeRewardS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LikeRewardVM @Inject constructor(private val likeRewardR: LikeRewardR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getLikeRewardStateFlow: MutableStateFlow<LikeRewardS> = MutableStateFlow(LikeRewardS.Empty)
    val _getLikeRewardStateFlow: StateFlow<LikeRewardS> = getLikeRewardStateFlow


    init {
        getLikeReward()
    }

    private fun getLikeReward() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getLikeRewardStateFlow.value = LikeRewardS.Loading
                likeRewardR.getLikeRewardR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getLikeRewardStateFlow.value = LikeRewardS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getLikeRewardStateFlow.value = LikeRewardS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}