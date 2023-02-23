package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.*
import com.cryptoxin.uistate.CommentRewardSumS
import com.cryptoxin.uistate.NoOfFollowerS
import com.cryptoxin.uistate.NoOfFollowingS
import com.cryptoxin.uistate.ProfileS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject





@HiltViewModel
class CommentRewardSumVM @Inject constructor(private val commentRewardSumR: CommentRewardSumR , private val walletR: WalletR) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getCommentRewardSumStateFlow: MutableStateFlow<CommentRewardSumS> = MutableStateFlow(
        CommentRewardSumS.Empty)
    val _getCommentRewardSumStateFlow: StateFlow<CommentRewardSumS> = getCommentRewardSumStateFlow




     fun getCommentRewardSum() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getCommentRewardSumStateFlow.value = CommentRewardSumS.Loading
                commentRewardSumR.getCommentRewardSumR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getCommentRewardSumStateFlow.value = CommentRewardSumS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getCommentRewardSumStateFlow.value = CommentRewardSumS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}