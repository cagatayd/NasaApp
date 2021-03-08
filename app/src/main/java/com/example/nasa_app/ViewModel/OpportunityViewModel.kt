package com.example.nasa_app.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.nasa_app.Model.Root
import com.example.nasa_app.Repository.NasaRepository
import com.example.nasa_app.Service.NasaService
import com.example.nasa_app.Util.AppContant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OpportunityViewModel(application: Application) : AndroidViewModel(application) {


    var getOpportunityyphoto = MutableLiveData<Root>()
    var filterCameraOfOpportunity = MutableLiveData<Root>()


    suspend fun getOpportunityPhotoList(page: String?){
        withContext(Dispatchers.Main){
            var nasaService = Retrofit.Builder()
                    .baseUrl(AppContant.SERVİCE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NasaService::class.java)
            NasaRepository(nasaService).getOpportunityphotoList(page).apply {
                getOpportunityyphoto.postValue(this.body())
            }
        }
    }

    suspend fun getFilterCameraOfOpportunityy(camera: String?){
        withContext(Dispatchers.Main){
            var nasaService = Retrofit.Builder()
                    .baseUrl(AppContant.SERVİCE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NasaService::class.java)
            NasaRepository(nasaService).getfilterCameraOfOpportunityPhoto(camera).apply {
                filterCameraOfOpportunity.postValue(this.body())
            }
        }
    }



}