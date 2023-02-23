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
import com.cryptoxin.data.bodymodel.CreateCommentBody
import com.cryptoxin.data.bodymodel.LikePostBody
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.*
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
class CreateCommentVM @Inject constructor(private val createCommentR: CreateCommentR, private val walletR: WalletR) :
    ViewModel() {

    private val getCreateCommentStateFlow: MutableStateFlow<CreateCommentS> = MutableStateFlow(
        CreateCommentS.Empty)
    val _getCreateCommentStateFlow: StateFlow<CreateCommentS> = getCreateCommentStateFlow


    fun getCreateComment(postId:String,messages:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val createCommentBody =
                    CreateCommentBody (myAddress = it.address , privateKey = it.privateKey, postId = postId, messages = messages)
                getCreateCommentStateFlow.value = CreateCommentS.Loading
                createCommentR.getCreateCommentR(
                    createCommentBody
                ).catch { e ->
                    getCreateCommentStateFlow.value = CreateCommentS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getCreateCommentStateFlow.value = CreateCommentS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}