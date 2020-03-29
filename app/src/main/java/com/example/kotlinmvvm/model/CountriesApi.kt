package com.example.kotlinmvvm.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi { //define the function that will be called toi retrieve info from backend
    @GET ("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>
}