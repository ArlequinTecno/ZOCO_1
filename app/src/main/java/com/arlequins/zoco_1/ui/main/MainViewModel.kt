package com.arlequins.zoco_1.ui.main

import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.data.SearchRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _searchText: MutableLiveData<String?> = MutableLiveData()
    val searchText: LiveData<String?> = _searchText

    private val searchRepository= SearchRepository()


    fun search(menu: Menu, destination: NavDestination) {
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModelScope.launch{
                    if (query != null) {
                        if (query.isNotEmpty()) {
                            when(destination.id){
                                R.id.nav_index ->{
                                    Log.i("Prueba", "en index")
                                    _searchText.postValue(searchRepository.search(query, "index"))
                                }
                                R.id.nav_category ->{
                                    Log.i("Prueba", "categorias")
                                    _searchText.postValue(searchRepository.search(query, "category"))
                                }
                                R.id.nav_my_products ->{
                                    Log.i("Prueba", "Mis productos")
                                    _searchText.postValue(searchRepository.search(query, "my_products"))
                                }
                            }
                        }
                    }
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModelScope.launch{
                    if (newText != null) {
                        if (newText.isNotEmpty()) {
                            when(destination.id){
                                R.id.nav_index ->{
                                    Log.i("Prueba", "en index")
                                    _searchText.postValue(searchRepository.search(newText, "index"))
                                }
                                R.id.nav_category ->{
                                    Log.i("Prueba", "categorias")
                                    _searchText.postValue(searchRepository.search(newText, "category"))
                                }
                                R.id.nav_my_products ->{
                                    Log.i("Prueba", "Mis productos")
                                    _searchText.postValue(searchRepository.search(newText, "my_products"))
                                }
                            }
                        }
                    }
                }
                return true
            }
        })
    }

}

