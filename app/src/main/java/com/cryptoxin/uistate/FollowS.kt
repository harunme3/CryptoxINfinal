package com.cryptoxin.uistate

import com.cryptoxin.data.models.followm.FollowM


sealed class FollowS {
    object Empty : FollowS()
    object Loading : FollowS()
    class Loaded(val data: FollowM) : FollowS()
    class Error(val message: String) : FollowS()
}