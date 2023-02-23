package com.cryptoxin.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.WalletIdS
import com.cryptoxin.uistate.WalletS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletVM  @Inject constructor (private val walletR: WalletR): ViewModel()  {



    private val getAllWalletStateFlow: MutableStateFlow<WalletS>
            = MutableStateFlow(WalletS.Empty)
    val _getAllWalletStateFlow: StateFlow<WalletS> = getAllWalletStateFlow

    private val getWalletStateFlow: MutableStateFlow<WalletIdS>
            = MutableStateFlow(WalletIdS.Empty)
    val _getWalletStateFlow: StateFlow<WalletIdS> = getWalletStateFlow


init {
    getAllWallet()
}

     fun getAllWallet() {

        viewModelScope.launch {

            getAllWalletStateFlow.value = WalletS.Loading

            walletR.getAllWallet(
            ).catch { e->
                getAllWalletStateFlow.value= WalletS.Error(message = "Exception $e")
            }.collect {
                    val getWalletData=it
                    getAllWalletStateFlow.value= WalletS.Loaded(data =getWalletData)

            }
        }
    }

     fun getWallet(walletId:Int) {

        viewModelScope.launch {

            getWalletStateFlow.value = WalletIdS.Loading

            walletR.getWallet(walletId = walletId
            ).catch { e->
                getWalletStateFlow.value= WalletIdS.Error(message = "Exception $e")
            }.collect {
                val getWalletData=it
                getWalletStateFlow.value= WalletIdS.Loaded(data =getWalletData)

            }
        }
    }



    fun createWallet(walletEntity: WalletEntity) = viewModelScope.launch(Dispatchers.IO) {
        walletR.insertWallet(walletEntity)
    }



    fun deleteWallet(walletEntity: WalletEntity){
        viewModelScope.launch(Dispatchers.IO) {
            walletR.deleteWallet(walletEntity)
        }
    }




}