package com.cryptoxin.uistate

import com.cryptoxin.data.models.commentrewardm.CommentRewardM


sealed class CommentRewardS {
    object Empty : CommentRewardS()
    object Loading : CommentRewardS()
    class Loaded(val data: CommentRewardM) : CommentRewardS()
    class Error(val message: String) : CommentRewardS()
}