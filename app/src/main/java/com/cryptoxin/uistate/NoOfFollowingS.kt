package com.cryptoxin.uistate

import com.cryptoxin.data.models.nooffollowing.NoOfFollowingM


sealed class NoOfFollowingS {
    object Empty : NoOfFollowingS()
    object Loading : NoOfFollowingS()
    class Loaded(val data: NoOfFollowingM) : NoOfFollowingS()
    class Error(val message: String) : NoOfFollowingS()
}