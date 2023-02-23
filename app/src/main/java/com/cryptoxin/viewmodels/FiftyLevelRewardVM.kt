package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.FiftyLevelRewardR
import com.cryptoxin.data.repository.FollowingR
import com.cryptoxin.data.repository.NoOfFollowerR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiftyLevelRewardVM @Inject constructor(private val fiftyLevelRewardR: FiftyLevelRewardR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getFiftyLevelRewardStateFlow: MutableStateFlow<FiftyLevelRewardS> = MutableStateFlow(
        FiftyLevelRewardS.Empty)
    val _getFiftyLevelRewardStateFlow: StateFlow<FiftyLevelRewardS> = getFiftyLevelRewardStateFlow


    init {
        getFollowing()
    }

    private fun getFollowing() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getFiftyLevelRewardStateFlow.value = FiftyLevelRewardS.Loading
                fiftyLevelRewardR.getFiftyLevelRewardR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getFiftyLevelRewardStateFlow.value = FiftyLevelRewardS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getFiftyLevelRewardStateFlow.value = FiftyLevelRewardS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}