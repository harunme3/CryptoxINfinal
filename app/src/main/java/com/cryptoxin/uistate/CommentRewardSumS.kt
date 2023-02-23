package com.cryptoxin.uistate

import com.cryptoxin.data.models.commentrewardsumm.CommentRewardSumM


sealed class CommentRewardSumS {
    object Empty : CommentRewardSumS()
    object Loading : CommentRewardSumS()
    class Loaded(val data: CommentRewardSumM) : CommentRewardSumS()
    class Error(val message: String) : CommentRewardSumS()
}