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
class SingleWalletVM  @Inject constructor (private val walletR: WalletR): ViewModel()  {




    private val getWalletStateFlow: MutableStateFlow<WalletIdS>
            = MutableStateFlow(WalletIdS.Empty)
    val _getWalletStateFlow: StateFlow<WalletIdS> = getWalletStateFlow




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



}