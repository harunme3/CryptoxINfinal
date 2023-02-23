package com.cryptoxin.uistate

import com.cryptoxin.data.models.profilem.ProfileM


sealed class ProfileS {
    object Empty : ProfileS()
    object Loading : ProfileS()
    class Loaded(val data: ProfileM) : ProfileS()
    class Error(val message: String) : ProfileS()
}