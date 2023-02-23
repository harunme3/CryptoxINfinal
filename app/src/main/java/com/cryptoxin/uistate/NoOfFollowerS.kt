package com.cryptoxin.uistate

import com.cryptoxin.data.models.nooffollower.NoOfFollowerM


sealed class NoOfFollowerS {
    object Empty : NoOfFollowerS()
    object Loading : NoOfFollowerS()
    class Loaded(val data: NoOfFollowerM) : NoOfFollowerS()
    class Error(val message: String) : NoOfFollowerS()
}