package com.cryptoxin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.repository.AllPostR
import com.cryptoxin.data.repository.AllUserR
import com.cryptoxin.uistate.AllPostS
import com.cryptoxin.uistate.AllUserS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllUserVM @Inject constructor(private val allUserR: AllUserR) : ViewModel() {

    private val getAllUserStateFlow: MutableStateFlow<AllUserS> = MutableStateFlow(AllUserS.Empty)
    val _getAllUserStateFlow: StateFlow<AllUserS> = getAllUserStateFlow

    init {
        getAllUser()
    }

    private fun getAllUser() {


        viewModelScope.launch {

            getAllUserStateFlow.value = AllUserS.Loading

            allUserR.getAllPostR(
            ).catch { e ->
                getAllUserStateFlow.value = AllUserS.Error(message = "Exception $e")
            }.collect {
                if (it.data != null) {
                    val finalData = it.data
                    getAllUserStateFlow.value = AllUserS.Loaded(data = finalData)
                }
            }
        }
    }

}