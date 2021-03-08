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

class SpiritViewModel(application: Application) : AndroidViewModel(application) {
    var getSpiritphoto = MutableLiveData<Root>()
    var filterCameraOfSpirit = MutableLiveData<Root>()

    suspend fun getSpiritPhotoList(page: String?){
        withContext(Dispatchers.Default){
            var nasaService = Retrofit.Builder()
                    .baseUrl(AppContant.SERVİCE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NasaService::class.java)
            NasaRepository(nasaService).getSpiritphotoList(page).apply {
                getSpiritphoto.postValue(this.body())
            }
        }
    }

    suspend fun getFilterCameraOfSpirit(camera: String?){
        withContext(Dispatchers.Default){
            var nasaService = Retrofit.Builder()
                    .baseUrl(AppContant.SERVİCE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NasaService::class.java)
            NasaRepository(nasaService).getfilterCameraOfSpiritPhoto(camera).apply {
                filterCameraOfSpirit.postValue(this.body())
            }
        }
    }
}