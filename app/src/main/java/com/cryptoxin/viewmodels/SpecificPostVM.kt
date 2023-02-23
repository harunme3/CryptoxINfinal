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
import com.cryptoxin.data.bodymodel.LikePostBody
import com.cryptoxin.data.bodymodel.SpecificPostBody
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
class SpecificPostVM @Inject constructor(private val specificPostR: SpecificPostR , private val walletR: WalletR) :
    ViewModel() {

    private val getSpecificPostStateFlow: MutableStateFlow<SpecificPostS> = MutableStateFlow(
        SpecificPostS.Empty)
    val _getSpecificPostStateFlow: StateFlow<SpecificPostS> = getSpecificPostStateFlow


    fun getSpecificPost(postId:String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val specificPostBody =
                    SpecificPostBody (myAddress = it.address , privateKey = it.privateKey, postId = postId)
                getSpecificPostStateFlow.value = SpecificPostS.Loading
                specificPostR.getSpecificPostR(
                    specificPostBody
                ).catch { e ->
                    getSpecificPostStateFlow.value = SpecificPostS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getSpecificPostStateFlow.value = SpecificPostS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}