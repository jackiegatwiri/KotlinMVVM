package com.example.kotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var  viewModel: ListViewModel //basically tells the compiler that we will instantiate this b4 using
    private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.countries.observe(this, Observer {countries ->
            countriesList.visibility = View.VISIBLE
            countries?.let { countryListAdapter.updateCountries(it)}
        })

        viewModel.countryLoadError.observe(this, Observer {isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE}
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })
    }
}
