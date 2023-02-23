package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.CinBalanceR
import com.cryptoxin.data.repository.NoOfFollowerR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.CinBalanceS
import com.cryptoxin.uistate.NoOfFollowerS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CinBalanceVM @Inject constructor(private val cinBalanceR: CinBalanceR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getCinBalanceStateFlow: MutableStateFlow<CinBalanceS> = MutableStateFlow(CinBalanceS.Empty)
    val _getCinBalanceStateFlow: StateFlow<CinBalanceS> = getCinBalanceStateFlow



     fun getCinBalance() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getCinBalanceStateFlow.value = CinBalanceS.Loading
                cinBalanceR.getCinBalanceR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getCinBalanceStateFlow.value = CinBalanceS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getCinBalanceStateFlow.value = CinBalanceS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}