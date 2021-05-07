package com.cahstudio.tesapotekk24.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahstudio.tesapotekk24.datasource.remote.model.AuthResponse
import com.cahstudio.tesapotekk24.repository.LoginRepository
import com.google.gson.Gson
import io.reactivex.rxjava3.kotlin.subscribeBy

class LoginViewModel(val loginRepo: LoginRepository): ViewModel() {

    fun login(username: String, password: String): LiveData<AuthResponse> {
        val authRespone = MutableLiveData<AuthResponse>()
        loginRepo.login(username, password).subscribeBy(
                onComplete = {
                    println("login done")
                },

                onError = {
                    it.printStackTrace()
                },

                onNext = {
                    val gson = Gson()
                    if (it.code() == 200) {
                        var auth = AuthResponse()
                        val status = it.body()?.get("status")?.asString

                        if (status == "true") {
                            val json = gson.toJson(it.body())
                            auth = gson.fromJson(json, AuthResponse::class.java)
                        } else {
                            auth.status = "false"
                        }

                        authRespone.value = auth
                    }
                }
        )

        return authRespone
    }
}