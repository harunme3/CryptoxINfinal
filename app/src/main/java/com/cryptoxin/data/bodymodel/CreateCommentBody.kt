package com.cryptoxin.data.bodymodel


data class CreateCommentBody(
    val myAddress: String,
    val privateKey: String,
    val postId : String,
    val messages : String,
)