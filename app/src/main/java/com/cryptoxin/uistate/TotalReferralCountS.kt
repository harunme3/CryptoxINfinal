package com.cryptoxin.uistate

import com.cryptoxin.data.models.totalreferralcountm.TotalReferralCountM


sealed class TotalReferralCountS {
    object Empty : TotalReferralCountS()
    object Loading : TotalReferralCountS()
    class Loaded(val data: TotalReferralCountM) : TotalReferralCountS()
    class Error(val message: String) : TotalReferralCountS()
}