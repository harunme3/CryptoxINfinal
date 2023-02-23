package com.cryptoxin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.repository.AllPostR
import com.cryptoxin.uistate.AllPostS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllPostVM @Inject constructor(private val allPostR: AllPostR) : ViewModel() {

    private val getAllPostStateFlow: MutableStateFlow<AllPostS> = MutableStateFlow(AllPostS.Empty)
    val _getAllPostStateFlow: StateFlow<AllPostS> = getAllPostStateFlow

    init {
        getAllPost()
    }

    private fun getAllPost() {


        viewModelScope.launch {

            getAllPostStateFlow.value = AllPostS.Loading

            allPostR.getAllPostR(
            ).catch { e ->
                getAllPostStateFlow.value = AllPostS.Error(message = "Exception $e")
            }.collect {
                if (it.data != null) {

                    val getAllPostData = it.data
                    getAllPostStateFlow.value = AllPostS.Loaded(data = getAllPostData)
                }
            }
        }
    }

}