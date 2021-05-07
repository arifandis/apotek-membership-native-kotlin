package com.cahstudio.tesapotekk24.repository

import com.cahstudio.tesapotekk24.datasource.remote.ApiService
import com.cahstudio.tesapotekk24.datasource.remote.model.GeneralResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class AddMemberRepository(private val api: ApiService) {

    fun addMember(partnerID: String, partnerCode: String, name: String, date: String): Observable<Response<GeneralResponse>> {
        return api.addMember(partnerID, partnerCode, name, date).toObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}