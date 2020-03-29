package com.example.kotlinmvvm.DependencyInjection

import com.example.kotlinmvvm.model.CountriesService
import com.example.kotlinmvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}