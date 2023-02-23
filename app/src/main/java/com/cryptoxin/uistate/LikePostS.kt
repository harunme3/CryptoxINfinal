package com.cryptoxin.uistate

import com.cryptoxin.data.models.likepostm.LikePostM


sealed class LikePostS {
    object Empty : LikePostS()
    object Loading : LikePostS()
    class Loaded(val data: LikePostM) : LikePostS()
    class Error(val message: String) : LikePostS()
}