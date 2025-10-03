package com.example.textres.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textres.Location.Coordinates
import com.example.textres.Location.LocationFlow
import com.example.textres.api.apiMethods
import com.example.textres.api.model.Data
import com.example.textres.room.Properties
import com.example.textres.room.Repository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class Viewmodel(private val locationFlow: LocationFlow ,private val api : apiMethods, private val repository: Repository) : ViewModel() {
    private val _properties = MutableStateFlow<Properties?>(null)
    val properties = _properties.asStateFlow()
    private val _location = MutableStateFlow<Coordinates?>(null)
    val location = _location.asStateFlow()
    private val _data = MutableStateFlow<Data?>(null)
    val data = _data.asStateFlow()
  init {
      getLocation()
      getData()
      getProperties()
  }
    fun getLocation(){
        viewModelScope.launch {
            locationFlow.getLocation().collect { it ->
                  _location.value = it
            }
        }
    }
    fun getData(){
         viewModelScope.launch {
           _data.value = api.getData()
         }
    }
    fun insertProperties(props : Properties){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProps(props)
        }
    }
    fun deleteProperties(props : Properties){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProps(props)
        }
    }
    fun getProperties(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProps().collect { it ->
                _properties.value = it

            }
        }
    }
}