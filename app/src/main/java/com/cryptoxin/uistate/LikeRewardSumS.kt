package com.cryptoxin.uistate

import com.cryptoxin.data.models.likerewardsumm.LikeRewardSumM


sealed class LikeRewardSumS {
    object Empty : LikeRewardSumS()
    object Loading : LikeRewardSumS()
    class Loaded(val data: LikeRewardSumM) : LikeRewardSumS()
    class Error(val message: String) : LikeRewardSumS()
}