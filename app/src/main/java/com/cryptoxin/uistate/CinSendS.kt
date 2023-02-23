package com.cryptoxin.uistate

import com.cryptoxin.data.models.cinsendm.CinSendM


sealed class CinSendS {
    object Empty : CinSendS()
    object Loading : CinSendS()
    class Loaded(val data: CinSendM) : CinSendS()
    class Error(val message: String) : CinSendS()
}