package com.cryptoxin.uistate

import com.cryptoxin.data.models.deletepostm.DeletePostM


sealed class DeletePostS {
    object Empty : DeletePostS()
    object Loading : DeletePostS()
    class Loaded(val data: DeletePostM) : DeletePostS()
    class Error(val message: String) : DeletePostS()
}