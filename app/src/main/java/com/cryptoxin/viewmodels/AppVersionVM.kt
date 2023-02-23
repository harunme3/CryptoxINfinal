package com.cryptoxin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.repository.AllPostR
import com.cryptoxin.data.repository.AppVersionR
import com.cryptoxin.uistate.AllPostS
import com.cryptoxin.uistate.AppVersionS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppVersionVM @Inject constructor(private val appVersionR: AppVersionR) : ViewModel() {

    private val getAppVersionStateFlow: MutableStateFlow<AppVersionS> = MutableStateFlow(AppVersionS.Empty)
    val _getAppVersionStateFlow: StateFlow<AppVersionS> = getAppVersionStateFlow

    init {
        getAppVersion()
    }

    private fun getAppVersion() {


        viewModelScope.launch {

            getAppVersionStateFlow.value = AppVersionS.Loading

            appVersionR.getAppVersionR(
            ).catch { e ->
                getAppVersionStateFlow.value = AppVersionS.Error(message = "Exception $e")
            }.collect {
                if (it.data != null) {
                    val finalData = it.data
                    getAppVersionStateFlow.value = AppVersionS.Loaded(data = finalData)
                }
            }
        }
    }

}