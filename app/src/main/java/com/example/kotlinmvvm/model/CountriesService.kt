package com.example.kotlinmvvm.model

import com.example.kotlinmvvm.DependencyInjection.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountriesService {
    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)

    }

    //return a list of a single country
    //single is an observable that emits only one object then close it

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}