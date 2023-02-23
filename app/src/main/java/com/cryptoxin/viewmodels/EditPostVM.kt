package com.cryptoxin.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.EditPostBody
import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.repository.EditPostR
import com.cryptoxin.data.repository.FollowR
import com.cryptoxin.data.repository.WalletR
import com.cryptoxin.uistate.EditPostS
import com.cryptoxin.uistate.FollowS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditPostVM @Inject constructor(private val editPostR: EditPostR , private val walletR: WalletR) :
    ViewModel() {

    private val getEditPostStateFlow: MutableStateFlow<EditPostS> = MutableStateFlow(
        EditPostS.Empty)
    val _getEditPostStateFlow: StateFlow<EditPostS> = getEditPostStateFlow


    private fun getEditPost(_id:String,_hashtag :String,_content :String,_imghash :String) {
        viewModelScope.launch {
            walletR.getWallet(
                walletId = 1
            ).catch { e ->
                Log.e("1111" , "failed to fetch $e")
            }.collect {
                Log.e("1111" , "Collected $it")
                val editPostBody =
                    EditPostBody (myAddress = it.address , privateKey = it.privateKey,
                          _id = _id,
                          _hashtag = _hashtag ,
                         _content =_content,
                        _imghash =_imghash
                    )
                getEditPostStateFlow.value = EditPostS.Loading
                editPostR.getEditPostR(
                    editPostBody
                ).catch { e ->
                    getEditPostStateFlow.value = EditPostS.Error(message = "Exception $e")
                }.collect {its->
                    if (its.data != null) {
                        val finalData = its.data
                        getEditPostStateFlow.value = EditPostS.Loaded(data = finalData)
                    }
                }

            }
        }

    }

}