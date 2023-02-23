package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.SignupBonusR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.SignupBonusS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignupBonusVM @Inject constructor(private val signupBonusR: SignupBonusR ) :
    ViewModel() {



    private val getSignupBonusStateFlow: MutableStateFlow<SignupBonusS> = MutableStateFlow(
        SignupBonusS.Empty)
    val _getSignupBonusStateFlow: StateFlow<SignupBonusS> = getSignupBonusStateFlow




     fun getSignupBonus(myAddress:String,privateKey:String) {
        viewModelScope.launch {
          val  addressPrivateKeyBody =
                AddressPrivateKeyBody(myAddress =myAddress , privateKey =privateKey)
            getSignupBonusStateFlow.value = SignupBonusS.Loading
            signupBonusR.getSignupBonusR(
                addressPrivateKeyBody
            ).catch { e ->
                getSignupBonusStateFlow.value = SignupBonusS.Error(message = "Exception $e")
            }.collect {its->
                if (its.data != null) {
                    val finalData = its.data
                    getSignupBonusStateFlow.value = SignupBonusS.Loaded(data = finalData)
                }
            }
        }

    }

}