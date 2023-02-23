package com.cryptoxin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.ImportAccountBody
import com.cryptoxin.data.models.importaccountmodel.ImportAccountModel
import com.cryptoxin.data.repository.ImportWalletRepo
import com.cryptoxin.uistate.ImportWalletState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ImportWalletViewModel  @Inject constructor (private val importWalletRepo: ImportWalletRepo): ViewModel()  {

    private val importWalletStateFlow: MutableStateFlow<ImportWalletState>
            = MutableStateFlow(ImportWalletState.Empty)
    val _importWalletStateFlow: StateFlow<ImportWalletState> = importWalletStateFlow

    fun importWalletCall(privateKey:String ,mnemonic:String) {

        val body =ImportAccountBody(privateKey=privateKey, mnemonic =mnemonic)
        viewModelScope.launch {

            importWalletStateFlow.value = ImportWalletState.Loading
            importWalletRepo.getImportWalletRepo(
               importAccountBody = body
            ).catch { e->
                    importWalletStateFlow.value=ImportWalletState.Error(message = "Exception $e")
                }.collect {
                    if (it.data!=null)
                    {

                        val importAccountData=it.data as ImportAccountModel
                        importWalletStateFlow.value=ImportWalletState.Loaded(data =importAccountData)
                    }
                }
        }
    }

}