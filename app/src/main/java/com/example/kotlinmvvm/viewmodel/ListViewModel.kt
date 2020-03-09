package com.example.kotlinmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvm.model.Country

//view model helps in creating liveData that users can subscribe to.. when the variable
//changes then this subscribers are notified

class ListViewModel:ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>() //basicall notify subscribers incase of an error
    val loading = MutableLiveData<Boolean>() //tells if backend is in the process of loading countries

    fun refresh()
    {
        fetchCountries()
    }

    //set your method for fetching data private then access it using refresh public
    //here we are fetching data from backend, if data is successfull set error to false
    //and loading to false and set mockdata tocountries then notify subscribers

    private fun fetchCountries() {
        val mockData = listOf(
            Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD")
        )
        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
 }