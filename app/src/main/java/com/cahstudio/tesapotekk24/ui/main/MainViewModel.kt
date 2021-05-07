package com.cahstudio.tesapotekk24.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahstudio.tesapotekk24.datasource.remote.model.MemberListResponse
import com.cahstudio.tesapotekk24.repository.MainRepository
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(private val mainRepo: MainRepository): ViewModel() {

    fun getMemberList(partnerID: String, partnerCode: String): LiveData<MemberListResponse> {
        val memberResponse = MutableLiveData<MemberListResponse>()
        mainRepo.getMemberList(partnerID, partnerCode).subscribeBy(
            onNext = {
                     if (it.code() == 200) {
                         memberResponse.value = it.body()
                     }
            },
            onError = {
                it.printStackTrace()
            },
            onComplete = {
                print("get member list done")
            }
        )

        return memberResponse
    }
}