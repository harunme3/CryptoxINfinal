package com.cryptoxin.uistate

import com.cryptoxin.data.models.commentsm.CommentsM


sealed class CommentsS {
    object Empty : CommentsS()
    object Loading : CommentsS()
    class Loaded(val data: CommentsM) : CommentsS()
    class Error(val message: String) : CommentsS()
}