package com.cryptoxin.uistate

import com.cryptoxin.data.models.postrewardsumm.PostRewardSumM


sealed class PostRewardSumS {
    object Empty : PostRewardSumS()
    object Loading : PostRewardSumS()
    class Loaded(val data: PostRewardSumM) : PostRewardSumS()
    class Error(val message: String) : PostRewardSumS()
}