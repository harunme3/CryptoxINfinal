package com.cryptoxin.uistate

import com.cryptoxin.data.models.imageupdatemodel.ImageUpdateModel


sealed class ImageUpdateState {
    object Empty : ImageUpdateState()
    object Loading : ImageUpdateState()
    class Loaded(val data: ImageUpdateModel) : ImageUpdateState()
    class Error(val message: String) : ImageUpdateState()
}