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
import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.datasource.remotedata.UserPostR
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.DeletePostR
import com.cryptoxin.data.repository.FollowR
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
class FollowVM @Inject constructor(private val followR: FollowR , private val walletR: WalletR) :
    ViewModel() {

    private val getFollowStateFlow: MutableStateFlow<FollowS> = MutableStateFlow(
        FollowS.Empty)
    val _getFollowStateFlow: StateFlow<FollowS> = getFollowStateFlow


     fun getFollow(friendAddress:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val followBody =
                    FollowBody (myAddress = it.address , privateKey = it.privateKey, friendaddress  = friendAddress )
                getFollowStateFlow.value = FollowS.Loading
                followR.getFollowR(
                    followBody
                ).catch { e ->
                    getFollowStateFlow.value = FollowS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getFollowStateFlow.value = FollowS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}