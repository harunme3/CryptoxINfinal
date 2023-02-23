package com.cryptoxin.uistate

import com.cryptoxin.data.models.followerm.FollowerM


sealed class FollowerS {
    object Empty : FollowerS()
    object Loading : FollowerS()
    class Loaded(val data: FollowerM) : FollowerS()
    class Error(val message: String) : FollowerS()
}