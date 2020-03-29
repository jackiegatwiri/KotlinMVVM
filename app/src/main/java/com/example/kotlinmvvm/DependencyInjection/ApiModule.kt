package com.example.kotlinmvvm.DependencyInjection

import com.example.kotlinmvvm.model.CountriesApi
import com.example.kotlinmvvm.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//what allows users to subscribe to and get notifications
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun provideCountriesService(): CountriesService {
        return CountriesService()
    }
}