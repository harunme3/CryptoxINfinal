package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CinSendBody
import com.cryptoxin.data.repository.CinSendR
import com.cryptoxin.data.repository.NoOfFollowerR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.CinSendS
import com.cryptoxin.uistate.NoOfFollowerS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CinSendVM @Inject constructor(private val cinSendR: CinSendR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getCinSendStateFlow: MutableStateFlow<CinSendS> = MutableStateFlow(CinSendS.Empty)
    val _getCinSendStateFlow: StateFlow<CinSendS> = getCinSendStateFlow




     fun getCinSend(receiver:String,amount:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
               val cinSendBody =
                    CinSendBody(myAddress = it.address , privateKey = it.privateKey, receiver =receiver , amount =amount )
                getCinSendStateFlow.value = CinSendS.Loading
                cinSendR.getCinSendR(
                    cinSendBody
                ).catch { e ->
                    getCinSendStateFlow.value = CinSendS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getCinSendStateFlow.value = CinSendS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}