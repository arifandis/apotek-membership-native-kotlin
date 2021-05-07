package com.cahstudio.tesapotekk24.ui.addmember

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahstudio.tesapotekk24.datasource.remote.model.GeneralResponse
import com.cahstudio.tesapotekk24.repository.AddMemberRepository
import io.reactivex.rxjava3.kotlin.subscribeBy

class AddMemberViewModel(private val addMemberRepo: AddMemberRepository): ViewModel() {

    fun addMember(partnerID: String, partnerCode: String, name: String, date: String): LiveData<GeneralResponse> {
        val addResponse = MutableLiveData<GeneralResponse>()
        addMemberRepo.addMember(partnerID, partnerCode, name, date).subscribeBy(
            onComplete = {
                print("add member done")
            },
            onError = {
                it.printStackTrace()
            },
            onNext = {

                if (it.code() == 200) {
                    addResponse.value = it.body()
                }
            }
        )

        return addResponse
    }
}