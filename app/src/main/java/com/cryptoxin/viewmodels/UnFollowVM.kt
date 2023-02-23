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
class UnFollowVM @Inject constructor(private val unFollowR: UnFollowR , private val walletR: WalletR) :
    ViewModel() {

    private val getUnFollowStateFlow: MutableStateFlow<UnFollowS> = MutableStateFlow(
        UnFollowS.Empty)
    val _getUnFollowStateFlow: StateFlow<UnFollowS> = getUnFollowStateFlow


     fun getUnFollow(friendAddress:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val followBody =
                    FollowBody (myAddress = it.address , privateKey = it.privateKey, friendaddress  = friendAddress )
                getUnFollowStateFlow.value = UnFollowS.Loading
                unFollowR.getUnFollowR(
                    followBody
                ).catch { e ->
                    getUnFollowStateFlow.value = UnFollowS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getUnFollowStateFlow.value = UnFollowS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}