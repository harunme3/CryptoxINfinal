package com.cryptoxin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.WalletIdS
import com.cryptoxin.uistate.WalletS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MultiWalletVM  @Inject constructor (private val walletR: WalletR): ViewModel()  {



    private val getAllWalletStateFlow: MutableStateFlow<WalletS>
            = MutableStateFlow(WalletS.Empty)
    val _getAllWalletStateFlow: StateFlow<WalletS> = getAllWalletStateFlow


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


}