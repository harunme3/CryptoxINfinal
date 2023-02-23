package com.cryptoxin.di
import com.cryptoxin.data.datasource.remotedata.*
import com.cryptoxin.utilities.UrlConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(allInterceptor: AllInterceptor ):OkHttpClient{
        return OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .addInterceptor(allInterceptor).build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder():Retrofit.Builder{
       return Retrofit.Builder().baseUrl(UrlConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideFakerAPI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): CreateWalletApiInterface {
        return  retrofitBuilder.client(okHttpClient).build().create(CreateWalletApiInterface::class.java)
    }


    @Singleton
    @Provides
    fun provideImportAccountAPI(retrofitBuilder: Retrofit.Builder): ImportWalletApiInterface {
        return  retrofitBuilder.build().create(ImportWalletApiInterface::class.java)
    }


    @Singleton
    @Provides
    fun provideGetAccount(retrofitBuilder: Retrofit.Builder): GetUserApiInterface {
        return  retrofitBuilder.build().create(GetUserApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRegistration(retrofitBuilder: Retrofit.Builder): RegistrationInterface {
        return  retrofitBuilder.build().create(RegistrationInterface::class.java)
    }


    var retrofitImageUpdate = Retrofit.Builder()
        .baseUrl("http://128.199.18.36:6000/")
        .addConverterFactory(GsonConverterFactory.create())


    @Singleton
    @Provides
    fun provideImageUpdate(retrofitBuilder: Retrofit.Builder): ImageUpdateInterface {
        return  retrofitImageUpdate.build().create(ImageUpdateInterface::class.java)
    }



    @Singleton
    @Provides
    fun provideAllPost(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): AllPostI {
        return  retrofitBuilder.client(okHttpClient).build().create(AllPostI::class.java)
    }

    @Singleton
    @Provides
    fun provideProfileUpdate(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): ProfileUpdateI {
        return  retrofitBuilder.client(okHttpClient).build().create(ProfileUpdateI::class.java)
    }

    @Singleton
    @Provides
    fun provideProfile(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): ProfileI {
        return  retrofitBuilder.client(okHttpClient).build().create(ProfileI::class.java)
    }

    @Singleton
    @Provides
    fun provideNoOfFollowing(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): NoOfFollowingI {
        return  retrofitBuilder.client(okHttpClient).build().create(NoOfFollowingI::class.java)
    }

    @Singleton
    @Provides
    fun provideNoOfFollower(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): NoOfFollowerI {
        return  retrofitBuilder.client(okHttpClient).build().create(NoOfFollowerI::class.java)
    }

    @Singleton
    @Provides
    fun provideDirectReferralCount(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): DirectReferralCountI {
        return  retrofitBuilder.client(okHttpClient).build().create(DirectReferralCountI::class.java)
    }
    @Singleton
    @Provides
    fun provideTotalReferralCount(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): TotalReferralCountI {
        return  retrofitBuilder.client(okHttpClient).build().create(TotalReferralCountI::class.java)
    }
    @Singleton
    @Provides
    fun provideReferralReward(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): ReferralRewardI {
        return  retrofitBuilder.client(okHttpClient).build().create(ReferralRewardI::class.java)
    }
    @Singleton
    @Provides
    fun provideBonusReward(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): BonusRewardI {
        return  retrofitBuilder.client(okHttpClient).build().create(BonusRewardI::class.java)
    }
    @Singleton
    @Provides
    fun providePostReward(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):PostRewardI {
        return  retrofitBuilder.client(okHttpClient).build().create(PostRewardI::class.java)
    }
    @Singleton
    @Provides
    fun provideLikeReward(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): LikeRewardI {
        return  retrofitBuilder.client(okHttpClient).build().create(LikeRewardI::class.java)
    }


    @Singleton
    @Provides
    fun provideCommentReward(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CommentRewardI {
        return  retrofitBuilder.client(okHttpClient).build().create(CommentRewardI::class.java)
    }

    @Singleton
    @Provides
    fun provideUserPost(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):UserPostI {
        return  retrofitBuilder.client(okHttpClient).build().create(UserPostI::class.java)
    }

    @Singleton
    @Provides
    fun provideFollower(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):FollowerI {
        return  retrofitBuilder.client(okHttpClient).build().create(FollowerI::class.java)
    }


    @Singleton
    @Provides
    fun provideFollowing(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):FollowingI {
        return  retrofitBuilder.client(okHttpClient).build().create(FollowingI::class.java)
    }

    @Singleton
    @Provides
    fun provideReferralHistory(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):ReferralHistoryI {
        return  retrofitBuilder.client(okHttpClient).build().create(ReferralHistoryI::class.java)
    }



    @Singleton
    @Provides
    fun provideDirectReferral(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):DirectReferralI {
        return  retrofitBuilder.client(okHttpClient).build().create(DirectReferralI::class.java)
    }

    @Singleton
    @Provides
    fun provideAllUser(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):AllUserI {
        return  retrofitBuilder.client(okHttpClient).build().create(AllUserI::class.java)
    }
    @Singleton
    @Provides
    fun provideDeletePost(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):DeletePostI {
        return  retrofitBuilder.client(okHttpClient).build().create(DeletePostI::class.java)
    }

    @Singleton
    @Provides
    fun provideLikePost(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):LikePostI {
        return  retrofitBuilder.client(okHttpClient).build().create(LikePostI::class.java)
    }

    @Singleton
    @Provides
    fun provideFollow(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):FollowI {
        return  retrofitBuilder.client(okHttpClient).build().create(FollowI::class.java)
    }


    @Singleton
    @Provides
    fun provideUnFollow(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):UnFollowI {
        return  retrofitBuilder.client(okHttpClient).build().create(UnFollowI::class.java)
    }

    @Singleton
    @Provides
    fun provideEditPost(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):EditPostI {
        return  retrofitBuilder.client(okHttpClient).build().create(EditPostI::class.java)
    }

    @Singleton
    @Provides
    fun provideFiftyLevelReward(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):FiftyLevelRewardI {
        return  retrofitBuilder.client(okHttpClient).build().create(FiftyLevelRewardI::class.java)
    }


    @Singleton
    @Provides
    fun provideBonusTime(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):BonusTimeI {
        return  retrofitBuilder.client(okHttpClient).build().create(BonusTimeI::class.java)
    }


    @Singleton
    @Provides
    fun provideDailyCheckInClaim(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):DailyCheckInClaimI {
        return  retrofitBuilder.client(okHttpClient).build().create(DailyCheckInClaimI::class.java)
    }

    @Singleton
    @Provides
    fun provideCreateCommentI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CreateCommentI {
        return  retrofitBuilder.client(okHttpClient).build().create(CreateCommentI::class.java)
    }


    @Singleton
    @Provides
    fun provideCommentsI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CommentsI {
        return  retrofitBuilder.client(okHttpClient).build().create(CommentsI::class.java)
    }


    @Singleton
    @Provides
    fun provideSpecificPostI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):SpecificPostI {
        return  retrofitBuilder.client(okHttpClient).build().create(SpecificPostI::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentRewardSumI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CommentRewardSumI {
        return  retrofitBuilder.client(okHttpClient).build().create(CommentRewardSumI::class.java)
    }

    @Singleton
    @Provides
    fun provideLikeRewardSumI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):LikeRewardSumI {
        return  retrofitBuilder.client(okHttpClient).build().create(LikeRewardSumI::class.java)
    }

    @Singleton
    @Provides
    fun providePostRewardSumI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):PostRewardSumI {
        return  retrofitBuilder.client(okHttpClient).build().create(PostRewardSumI::class.java)
    }

    @Singleton
    @Provides
    fun provideCinSendI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CinSendI {
        return  retrofitBuilder.client(okHttpClient).build().create(CinSendI::class.java)
    }


    @Singleton
    @Provides
    fun provideCinBalance(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CinBalanceI {
        return  retrofitBuilder.client(okHttpClient).build().create(CinBalanceI::class.java)
    }


    @Singleton
    @Provides
    fun provideTrxHistory(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):TrxHistoryI {
        return  retrofitBuilder.client(okHttpClient).build().create(TrxHistoryI::class.java)
    }

    @Singleton
    @Provides
    fun provideCheckUpdateUserName(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):CheckUpdateUserNameI {
        return  retrofitBuilder.client(okHttpClient).build().create(CheckUpdateUserNameI::class.java)
    }


    @Singleton
    @Provides
    fun provideSignupBonus(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):SignupBonusI {
        return  retrofitBuilder.client(okHttpClient).build().create(SignupBonusI::class.java)
    }

    @Singleton
    @Provides
    fun provideUnfollowList(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):UnfollowListI {
        return  retrofitBuilder.client(okHttpClient).build().create(UnfollowListI::class.java)
    }



    var retrofitAppUpdate = Retrofit.Builder()
        .baseUrl("https://api.tradekia.com/api/")
        .addConverterFactory(GsonConverterFactory.create())


    @Singleton
    @Provides
    fun provideAppVersion(retrofitBuilder: Retrofit.Builder): AppVersionI {
        return  retrofitAppUpdate.build().create(AppVersionI::class.java)
    }




}