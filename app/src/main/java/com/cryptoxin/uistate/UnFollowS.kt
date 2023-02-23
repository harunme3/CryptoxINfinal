package com.cryptoxin.uistate

import com.cryptoxin.data.models.userpostm.UserPostM


sealed class UnFollowS {
    object Empty : UnFollowS()
    object Loading : UnFollowS()
    class Loaded(val data: UserPostM) : UnFollowS()
    class Error(val message: String) : UnFollowS()
}