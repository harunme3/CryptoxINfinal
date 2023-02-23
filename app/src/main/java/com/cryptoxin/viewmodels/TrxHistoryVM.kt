package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.TrxHistoryR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.TrxHistoryS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrxHistoryVM @Inject constructor(
    private val trxHistoryR: TrxHistoryR ,
    private val walletR: WalletR
) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getTrxHistoryStateFlow: MutableStateFlow<TrxHistoryS> =
        MutableStateFlow(TrxHistoryS.Empty)
    val _getTrxHistoryStateFlow: StateFlow<TrxHistoryS> = getTrxHistoryStateFlow


    init {
        getTrxHistory()
    }

    private fun getTrxHistory() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getTrxHistoryStateFlow.value = TrxHistoryS.Loading
                trxHistoryR.getTrxHistoryR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getTrxHistoryStateFlow.value = TrxHistoryS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getTrxHistoryStateFlow.value = TrxHistoryS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}