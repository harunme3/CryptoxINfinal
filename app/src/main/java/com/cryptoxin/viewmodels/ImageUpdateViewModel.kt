package com.cryptoxin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoxin.data.bodymodel.GetUserModelBody
import com.cryptoxin.data.models.getuser.GetUserModel
import com.cryptoxin.data.models.imageupdatemodel.ImageUpdateModel
import com.cryptoxin.data.repository.GetUserRepo
import com.cryptoxin.data.repository.ImageUpdateRepo
import com.cryptoxin.uistate.GetUserState
import com.cryptoxin.uistate.ImageUpdateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class ImageUpdateViewModel  @Inject constructor (private val imageUpdateRepo: ImageUpdateRepo): ViewModel()  {



    private val getImageUpdateStateFlow: MutableStateFlow<ImageUpdateState>
            = MutableStateFlow(ImageUpdateState.Empty)
    val _getUserStateFlow: StateFlow<ImageUpdateState> = getImageUpdateStateFlow

    fun getImageUpdateCall(
        file:List<MultipartBody.Part>,
        myAddress: RequestBody,
        privateKey:RequestBody,
        type:RequestBody, _content:RequestBody,
        _hashtag:RequestBody,
        videoHash:RequestBody
    ) {

        viewModelScope.launch {

            getImageUpdateStateFlow.value = ImageUpdateState.Loading

            imageUpdateRepo.getImageUpdateRepo(
                 file = file,
                 myAddress = myAddress,
                 privateKey = privateKey,
                 type = type,
                _content=_content,
                _hashtag=_hashtag,
                videoHash=videoHash,

            )
                .catch { e->
                getImageUpdateStateFlow.value= ImageUpdateState.Error(message = "Exception $e")
            }.collect {
                if (it.data!=null)
                {

                    val imageUpdateData=it.data as ImageUpdateModel
                    getImageUpdateStateFlow.value= ImageUpdateState.Loaded(data =imageUpdateData)
                }
            }
        }
    }

}