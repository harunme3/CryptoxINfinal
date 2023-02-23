package com.cryptoxin.uistate

import com.cryptoxin.data.models.referralrewardm.ReferralRewardM


sealed class ReferralRewardS {
    object Empty : ReferralRewardS()
    object Loading : ReferralRewardS()
    class Loaded(val data: ReferralRewardM) : ReferralRewardS()
    class Error(val message: String) : ReferralRewardS()
}