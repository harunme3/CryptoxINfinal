package com.cryptoxin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.CheckUpdateUserNameBody
import com.cryptoxin.data.repository.CheckUpdateUserNameR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.CheckUpdateUserNameS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CheckUpdateUserNameVM @Inject constructor(private val checkUpdateUserNameR: CheckUpdateUserNameR) :
    ViewModel() {

    private val getCheckUpdateUserNameStateFlow: MutableStateFlow<CheckUpdateUserNameS> = MutableStateFlow(
        CheckUpdateUserNameS.Empty)
    val _getCheckUpdateUserNameStateFlow: StateFlow<CheckUpdateUserNameS> = getCheckUpdateUserNameStateFlow


    fun getCheckUpdateUserName(myAddress:String,privateKey:String,userName:String) {
        viewModelScope.launch {

            val likePostBody =
                CheckUpdateUserNameBody (myAddress = myAddress, privateKey = privateKey, userName =userName )
            getCheckUpdateUserNameStateFlow.value = CheckUpdateUserNameS.Loading
            checkUpdateUserNameR.getCheckUpdateUserNameR(
                likePostBody
            ).catch { e ->
                getCheckUpdateUserNameStateFlow.value = CheckUpdateUserNameS.Error(message = "Exception $e")
            }.collect {its->
                if (its.data != null) {
                    val finalData = its.data
                    getCheckUpdateUserNameStateFlow.value = CheckUpdateUserNameS.Loaded(data = finalData)
                }
            }
        }

    }

}