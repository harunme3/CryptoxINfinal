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
import com.cryptoxin.data.bodymodel.DeletePostBody
import com.cryptoxin.data.bodymodel.LikePostBody
import com.cryptoxin.data.datasource.remotedata.UserPostR
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.DeletePostR
import com.cryptoxin.data.repository.LikePostR
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
class LikePostVM @Inject constructor(private val likePostR: LikePostR , private val walletR: WalletR) :
    ViewModel() {

    private val getLikePostStateFlow: MutableStateFlow<LikePostS> = MutableStateFlow(
        LikePostS.Empty)
    val _getLikePostStateFlow: StateFlow<LikePostS> = getLikePostStateFlow


     fun getLikePost(postId:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val likePostBody =
                    LikePostBody (myAddress = it.address , privateKey = it.privateKey, PostId = postId)
                getLikePostStateFlow.value = LikePostS.Loading
                likePostR.getLikePostR(
                    likePostBody
                ).catch { e ->
                    getLikePostStateFlow.value = LikePostS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getLikePostStateFlow.value = LikePostS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}