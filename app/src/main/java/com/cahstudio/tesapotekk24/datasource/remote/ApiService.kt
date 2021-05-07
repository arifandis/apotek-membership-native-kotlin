package com.cahstudio.tesapotekk24.datasource.remote

import com.cahstudio.tesapotekk24.datasource.remote.model.GeneralResponse
import com.cahstudio.tesapotekk24.datasource.remote.model.MemberListResponse
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("member/login")
    fun login(@Field("username") username: String
              ,@Field("password") password: String): Single<Response<JsonObject>>

    @FormUrlEncoded
    @POST("member/list")
    fun memberList(@Field("partnerID") partnerID: String
                   ,@Field("partnerCode") partnerCode: String): Single<Response<MemberListResponse>>

    @FormUrlEncoded
    @POST("member/add")
    fun addMember(@Field("partnerID") partnerID: String
                  ,@Field("partnerCode") partnerCode: String
                  ,@Field("name") name: String
                  ,@Field("dateOfBirth") dateOfBirth: String): Single<Response<GeneralResponse>>

}