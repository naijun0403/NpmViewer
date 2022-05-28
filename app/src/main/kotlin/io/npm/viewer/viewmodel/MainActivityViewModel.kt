package io.npm.viewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.npm.viewer.model.ServiceModel
import io.npm.viewer.repository.MainActivityRepository
import io.npm.viewer.repository.NpmRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<ServiceModel>? = null

    fun getSearchData(packageName: String) : LiveData<ServiceModel>? {
        servicesLiveData = NpmRepository.getSearchDataFromNpm(packageName)
        return servicesLiveData
    }

}