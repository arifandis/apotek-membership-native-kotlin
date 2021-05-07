package com.cahstudio.tesapotekk24.repository

import com.cahstudio.tesapotekk24.datasource.remote.ApiService
import com.cahstudio.tesapotekk24.datasource.remote.model.GeneralResponse
import com.cahstudio.tesapotekk24.datasource.remote.model.MemberListResponse
import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class LoginRepository(private val api: ApiService) {

    fun login(username: String, password: String): Observable<Response<JsonObject>> {
        return api.login(username, password).toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMemberList(partnerID: String, partnerCode: String): Observable<Response<MemberListResponse>> {
        return api.memberList(partnerID, partnerCode).toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun addMember(partnerID: String, partnerCode: String, name: String, dateOfBirth: String): Observable<Response<GeneralResponse>> {
        return api.addMember(partnerID, partnerCode, name, dateOfBirth).toObservable()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}