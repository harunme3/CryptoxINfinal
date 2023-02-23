package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.TotalReferralCountR
import com.cryptoxin.uistate.TotalReferralCountS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TotalReferralCountVM @Inject constructor(private val totalReferralCountR: TotalReferralCountR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getTotalReferralCountStateFlow: MutableStateFlow<TotalReferralCountS> = MutableStateFlow(
        TotalReferralCountS.Empty
    )
    val _getTotalReferralCountStateFlow: StateFlow<TotalReferralCountS> = getTotalReferralCountStateFlow



     fun getTotalReferralCount() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getTotalReferralCountStateFlow.value = TotalReferralCountS.Loading
                totalReferralCountR.getTotalReferralCountR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getTotalReferralCountStateFlow.value =
                        TotalReferralCountS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getTotalReferralCountStateFlow.value =
                            TotalReferralCountS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}