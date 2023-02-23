package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.PostRewardR
import com.cryptoxin.uistate.PostRewardS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostRewardVM @Inject constructor(private val postRewardR: PostRewardR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getPostRewardStateFlow: MutableStateFlow<PostRewardS> = MutableStateFlow(PostRewardS.Empty)
    val _getPostRewardStateFlow: StateFlow<PostRewardS> = getPostRewardStateFlow


    init {
        getPostReward()
    }

    private fun getPostReward() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getPostRewardStateFlow.value = PostRewardS.Loading
                postRewardR.getPostRewardR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getPostRewardStateFlow.value = PostRewardS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getPostRewardStateFlow.value = PostRewardS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}