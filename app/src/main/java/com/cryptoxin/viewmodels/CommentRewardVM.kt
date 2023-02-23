package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.repository.CommentRewardR
import com.cryptoxin.uistate.CommentRewardS
import com.cryptoxin.data.repository.WalletR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommentRewardVM @Inject constructor(
    private val commentRewardR: CommentRewardR ,
    private val walletR: WalletR
) :
    ViewModel() {

    var addressPrivateKeyBody: AddressPrivateKeyBody by mutableStateOf(
        AddressPrivateKeyBody(
            "" ,
            ""
        )
    )

    private val getCommentRewardStateFlow: MutableStateFlow<CommentRewardS> =
        MutableStateFlow(CommentRewardS.Empty)
    val _getCommentRewardStateFlow: StateFlow<CommentRewardS> = getCommentRewardStateFlow


    init {
        getCommentReward()
    }

    private fun getCommentReward() {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                addressPrivateKeyBody =
                    AddressPrivateKeyBody(myAddress = it.address , privateKey = it.privateKey)
                getCommentRewardStateFlow.value = CommentRewardS.Loading
                commentRewardR.getCommentRewardR(
                    addressPrivateKeyBody
                ).catch { e ->
                    getCommentRewardStateFlow.value = CommentRewardS.Error(message = "Exception $e")
                }.collect {
                    if (it.data != null) {
                        val finalData = it.data
                        getCommentRewardStateFlow.value = CommentRewardS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}