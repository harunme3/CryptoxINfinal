package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.UnfollowListBody
import com.cryptoxin.data.repository.NoOfFollowingR
import com.cryptoxin.data.repository.UnfollowListR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.NoOfFollowingS
import com.cryptoxin.uistate.UnFollowListS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UnfollowListVM @Inject constructor(
    private val unfollowListR: UnfollowListR ,
    private val walletR: WalletR
) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getUnfollowListStateFlow: MutableStateFlow<UnFollowListS> = MutableStateFlow(
        UnFollowListS.Empty
    )
    val _getUnfollowListStateFlow: StateFlow<UnFollowListS> = getUnfollowListStateFlow


    fun getUnfollowList() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                unfollowListR.getUnfollowStream(
                    addressPrivateKeyBody
                )


            }
        }

    }

}