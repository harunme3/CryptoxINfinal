package com.cryptoxin.uistate

import com.cryptoxin.data.models.likerewardm.LikeRewardM


sealed class LikeRewardS {
    object Empty : LikeRewardS()
    object Loading : LikeRewardS()
    class Loaded(val data: LikeRewardM) : LikeRewardS()
    class Error(val message: String) : LikeRewardS()
}