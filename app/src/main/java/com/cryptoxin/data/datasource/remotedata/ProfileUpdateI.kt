package com.cryptoxin.data.datasource.remotedata


import com.cryptoxin.data.bodymodel.ProfileUpdateBody
import com.cryptoxin.data.models.profileupdatem.ProfileUpdateM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ProfileUpdateI {

    @POST("profileupdate")
    suspend fun setProfileUpdateI(@Body profileUpdateBody: ProfileUpdateBody): Response<ProfileUpdateM>
}