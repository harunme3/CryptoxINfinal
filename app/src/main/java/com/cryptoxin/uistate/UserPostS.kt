package com.cryptoxin.uistate

import com.cryptoxin.data.models.userpostm.UserPostM


sealed class UserPostS {
    object Empty : UserPostS()
    object Loading : UserPostS()
    class Loaded(val data: UserPostM) : UserPostS()
    class Error(val message: String) : UserPostS()
}