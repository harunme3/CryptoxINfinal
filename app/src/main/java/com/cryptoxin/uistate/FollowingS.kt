package com.cryptoxin.uistate

import com.cryptoxin.data.models.followingm.FollowingM


sealed class FollowingS {
    object Empty : FollowingS()
    object Loading : FollowingS()
    class Loaded(val data: FollowingM) : FollowingS()
    class Error(val message: String) : FollowingS()
}