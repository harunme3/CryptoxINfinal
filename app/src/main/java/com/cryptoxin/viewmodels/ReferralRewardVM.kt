package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.ReferralRewardR
import com.cryptoxin.uistate.ReferralRewardS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReferralRewardVM @Inject constructor(private val referralRewardR: ReferralRewardR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getReferralRewardStateFlow: MutableStateFlow<ReferralRewardS> = MutableStateFlow(
        ReferralRewardS.Empty
    )
    val _getReferralRewardStateFlow: StateFlow<ReferralRewardS> = getReferralRewardStateFlow



     fun getReferralReward() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getReferralRewardStateFlow.value = ReferralRewardS.Loading
                referralRewardR.getReferralRewardR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getReferralRewardStateFlow.value =
                        ReferralRewardS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getReferralRewardStateFlow.value = ReferralRewardS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}