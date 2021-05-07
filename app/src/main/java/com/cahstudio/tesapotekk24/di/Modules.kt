package com.cahstudio.tesapotekk24.di

import com.cahstudio.tesapotekk24.datasource.remote.ApiService
import com.cahstudio.tesapotekk24.repository.AddMemberRepository
import com.cahstudio.tesapotekk24.repository.LoginRepository
import com.cahstudio.tesapotekk24.repository.MainRepository
import com.cahstudio.tesapotekk24.ui.addmember.AddMemberViewModel
import com.cahstudio.tesapotekk24.ui.login.LoginViewModel
import com.cahstudio.tesapotekk24.ui.main.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { AddMemberViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository(get()) }
    single { MainRepository(get()) }
    single { AddMemberRepository(get()) }
}

val apiModule = module {
    fun provideUsedApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { provideUsedApi(get()) }
}

val networkModule = module {
    fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api-membership.k24.co.id:3121/")
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(factory))
                .build()
    }

    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
}