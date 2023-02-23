package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.DirectReferralR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.DirectReferralS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectReferralVM @Inject constructor(private val directReferralR: DirectReferralR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getDirectReferralStateFlow: MutableStateFlow<DirectReferralS> = MutableStateFlow(
        DirectReferralS.Empty)
    val _getDirectReferralStateFlow: StateFlow<DirectReferralS> = getDirectReferralStateFlow




     fun getDirectReferral(myAddress:String) {
        viewModelScope.launch {

            addressPrivateKeyBody =
                AddressPrivateKeyBody(myAddress = myAddress , privateKey = "")
            getDirectReferralStateFlow.value = DirectReferralS.Loading
            directReferralR.getDirectReferralR(
                addressPrivateKeyBody
            ).catch { e ->
                getDirectReferralStateFlow.value = DirectReferralS.Error(message = "Exception $e")
            }.collect {its->
                if (its.data != null) {
                    val finalData = its.data
                    getDirectReferralStateFlow.value = DirectReferralS.Loaded(data = finalData)
                }
            }
        }

    }

}