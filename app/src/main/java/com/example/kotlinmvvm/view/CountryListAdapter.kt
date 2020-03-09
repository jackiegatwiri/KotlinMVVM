package com.example.kotlinmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries : ArrayList<Country>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
//telling the adapter to re-do the whole list and add new countries
    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    //basically creates the CountryViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)

    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //the view is basically the view that we have created on item_country
        private val countryName = view.name
        fun bind(country: Country){
            countryName.text = country.countryName

        }
    }
}