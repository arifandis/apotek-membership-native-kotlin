package com.cahstudio.tesapotekk24.repository

import com.cahstudio.tesapotekk24.datasource.remote.ApiService
import com.cahstudio.tesapotekk24.datasource.remote.model.MemberListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MainRepository(private val api: ApiService) {

    fun getMemberList(partnerID: String, partnerCode: String): Observable<Response<MemberListResponse>> {
        return api.memberList(partnerID, partnerCode).toObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}