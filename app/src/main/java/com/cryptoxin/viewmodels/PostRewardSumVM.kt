package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.*
import com.cryptoxin.uistate.NoOfFollowerS
import com.cryptoxin.uistate.NoOfFollowingS
import com.cryptoxin.uistate.PostRewardSumS
import com.cryptoxin.uistate.ProfileS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject





@HiltViewModel
class PostRewardSumVM @Inject constructor(private val postRewardSumR: PostRewardSumR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getPostRewardSumStateFlow: MutableStateFlow<PostRewardSumS> = MutableStateFlow(
        PostRewardSumS.Empty)
    val _getPostRewardSumStateFlow: StateFlow<PostRewardSumS> = getPostRewardSumStateFlow




     fun getPostRewardSum() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getPostRewardSumStateFlow.value = PostRewardSumS.Loading
                postRewardSumR.getPostRewardSumR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getPostRewardSumStateFlow.value = PostRewardSumS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getPostRewardSumStateFlow.value = PostRewardSumS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}