package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.*
import com.cryptoxin.uistate.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject





@HiltViewModel
class LikeRewardSumVM @Inject constructor(private val likeRewardSumR: LikeRewardSumR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getLikeRewardSumStateFlow: MutableStateFlow<LikeRewardSumS> = MutableStateFlow(
        LikeRewardSumS.Empty)
    val _getLikeRewardSumStateFlow: StateFlow<LikeRewardSumS> = getLikeRewardSumStateFlow




     fun getLikeRewardSum() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getLikeRewardSumStateFlow.value = LikeRewardSumS.Loading
                likeRewardSumR.getLikeRewardSumR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getLikeRewardSumStateFlow.value = LikeRewardSumS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getLikeRewardSumStateFlow.value = LikeRewardSumS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}