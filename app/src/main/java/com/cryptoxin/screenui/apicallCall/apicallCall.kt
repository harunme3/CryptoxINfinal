package com.cryptoxin.screenui.apicallCall

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.TestInterface
import com.cryptoxin.data.models.imageupdatemodel.ImageUpdateModel
import com.cryptoxin.data.models.signupbonusm.SignupBonusM
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {

     fun signupbonus(
        myAddress: String,
        privateKey: String,
        ctx: Context,
    ) {
        var url = "http://128.199.18.36:8555/"
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            // as we are sending data in json format so
            // we have to add Gson converter factory
            .addConverterFactory(GsonConverterFactory.create())
            // at last we are building our retrofit builder.
            .build()
        // below the line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(TestInterface::class.java)
        // passing data from our text fields to our model class.

        // calling a method to create an update and passing our model class.
        val addressPrivateKeyBody=AddressPrivateKeyBody(myAddress =myAddress, privateKey = privateKey )
        val call: Call<SignupBonusM> = retrofitAPI.getTestInterface(
            addressPrivateKeyBody
        )
        // on below line we are executing our method.
        call!!.enqueue(object : Callback<SignupBonusM?> {
            override fun onResponse(call: Call<SignupBonusM?>?, response: Response<SignupBonusM?>) {
                // this method is called when we get response from our api.
                Toast.makeText(ctx, "Data posted to API", Toast.LENGTH_SHORT).show()
                // we are getting a response from our body and
                // passing it to our model class.
                Log.d("5555", response.body().toString())
                val model: SignupBonusM? = response.body()
                // on below line we are getting our data from model class
                // and adding it to our string.



            }

            override fun onFailure(call: Call<SignupBonusM?>?, t: Throwable) {
                // we get error response from API.


                Log.d("5555","Error found is : " + t.message)
            }
        })

    }
}