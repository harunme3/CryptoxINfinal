package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.BonusRewardR
import com.cryptoxin.uistate.BonusRewardS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class BonusRewardVM @Inject constructor(private val bonusRewardR: BonusRewardR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getBonusRewardStateFlow: MutableStateFlow<BonusRewardS> = MutableStateFlow(
        BonusRewardS.Empty)
    val _getBonusRewardStateFlow: StateFlow<BonusRewardS> = getBonusRewardStateFlow


    init {
        getBonusReward()
    }

    private fun getBonusReward() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getBonusRewardStateFlow.value = BonusRewardS.Loading
                bonusRewardR.getBonusRewardR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getBonusRewardStateFlow.value = BonusRewardS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getBonusRewardStateFlow.value = BonusRewardS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}