package com.cryptoxin.uistate

import com.cryptoxin.data.models.directreferralcountm.DirectReferralCountM


sealed class DirectReferralCountS {
    object Empty : DirectReferralCountS()
    object Loading : DirectReferralCountS()
    class Loaded(val data: DirectReferralCountM) : DirectReferralCountS()
    class Error(val message: String) : DirectReferralCountS()
}