package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.DirectReferralCountR
import com.cryptoxin.uistate.DirectReferralCountS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DirectReferralCountVM @Inject constructor(private val directReferralCountR: DirectReferralCountR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getDirectReferralCountStateFlow: MutableStateFlow<DirectReferralCountS> = MutableStateFlow(
        DirectReferralCountS.Empty
    )
    val _getDirectReferralCountStateFlow: StateFlow<DirectReferralCountS> = getDirectReferralCountStateFlow



     fun getDirectReferralCount() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getDirectReferralCountStateFlow.value = DirectReferralCountS.Loading
                directReferralCountR.getDirectReferralCountR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getDirectReferralCountStateFlow.value =
                        DirectReferralCountS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getDirectReferralCountStateFlow.value =
                            DirectReferralCountS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}