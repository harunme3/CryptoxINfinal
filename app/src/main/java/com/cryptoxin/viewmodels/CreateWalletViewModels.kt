package com.cryptoxin.viewmodels
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import com.cryptoxin.data.models.createaccountmodel.CreateAccount
import com.cryptoxin.data.repository.CreateWalletApiRepo
import com.cryptoxin.uistate.CreateWalletState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModels @Inject constructor (private val createWalletApiRepo: CreateWalletApiRepo): ViewModel()  {

    private val createWalletStateFlow:MutableStateFlow<CreateWalletState>
            = MutableStateFlow(CreateWalletState.Empty)
    val _createWalletStateFlow:StateFlow<CreateWalletState> = createWalletStateFlow




    init {
        viewModelScope.launch {
            createWalletStateFlow.value = CreateWalletState.Loading
            createWalletApiRepo.getCreateWalletRepo()
                .catch { e->
                    createWalletStateFlow.value=CreateWalletState.Error(message = "Exception $e")
                }.collect {
                if (it.data!=null)
                {
                    val createAccountData=it.data as CreateAccount
                    createWalletStateFlow.value=CreateWalletState.Loaded(data =createAccountData)
                }
                }
        }
    }


}
