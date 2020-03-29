package com.example.kotlinmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvm.DependencyInjection.DaggerApiComponent
import com.example.kotlinmvvm.model.CountriesService
import com.example.kotlinmvvm.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//view model helps in creating liveData that users can subscribe to.. when the variable
//changes then this subscribers are notified

class ListViewModel:ViewModel() {

    //viewmodel is getting info from the service, when viewmodel is closed it needs to be cleared using disposable
    @Inject
    lateinit var countriesService: CountriesService

    init {
        DaggerApiComponent.create().inject(this)
    }
    private val disposable = CompositeDisposable()

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
        loading.value = true
        //get info from get countries
        //we do not want the information that we get to be returned on the main thread of our application
        //get the data in the backgoround thread then show the result in the main thread
        disposable.add(countriesService.getCountries()
            .subscribeOn(Schedulers.newThread()) //create a background thread to load the data
            .observeOn(AndroidSchedulers.mainThread()) //un that thread on main for user to see
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(value: List<Country>?) {
                    countries.value = value
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable?) {
                    countryLoadError.value = true
                    loading.value = false
                }

            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
 }