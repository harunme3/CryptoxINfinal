package com.cryptoxin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.DeletePostBody
import com.cryptoxin.data.repository.DeletePostR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.DeletePostS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeletePostVM @Inject constructor(private val deletePostR: DeletePostR , private val walletR: WalletR) :
    ViewModel() {

    private val getDeletePostStateFlow: MutableStateFlow<DeletePostS> = MutableStateFlow(
        DeletePostS.Empty)
    val _getDeletePostStateFlow: StateFlow<DeletePostS> = getDeletePostStateFlow


     fun getDeletePost(postId:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
              val deletePostBody =
                   DeletePostBody (myAddress = it.address , privateKey = it.privateKey, PostId = postId)
                getDeletePostStateFlow.value = DeletePostS.Loading
                deletePostR.getDeletePostR(
                    deletePostBody
                ).catch { e ->
                    getDeletePostStateFlow.value = DeletePostS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getDeletePostStateFlow.value = DeletePostS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}