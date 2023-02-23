package com.cryptoxin.uistate

import com.cryptoxin.data.models.postrewardm.PostRewardM


sealed class PostRewardS {
    object Empty : PostRewardS()
    object Loading : PostRewardS()
    class Loaded(val data: PostRewardM) : PostRewardS()
    class Error(val message: String) : PostRewardS()
}