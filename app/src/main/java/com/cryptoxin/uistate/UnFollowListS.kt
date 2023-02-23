package com.cryptoxin.uistate

import com.cryptoxin.data.models.unfollowlistm.UnfollowListM


sealed class UnFollowListS {
    object Empty : UnFollowListS()
    object Loading : UnFollowListS()
    class Loaded(val data: UnfollowListM) : UnFollowListS()
    class Error(val message: String) : UnFollowListS()
}