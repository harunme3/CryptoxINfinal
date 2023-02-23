package com.cryptoxin.uistate

import com.cryptoxin.data.models.createcommentm.CreateCommentM


sealed class CreateCommentS {
    object Empty : CreateCommentS()
    object Loading : CreateCommentS()
    class Loaded(val data: CreateCommentM) : CreateCommentS()
    class Error(val message: String) : CreateCommentS()
}