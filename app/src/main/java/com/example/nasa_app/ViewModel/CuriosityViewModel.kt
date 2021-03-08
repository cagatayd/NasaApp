package com.example.nasa_app.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.nasa_app.Model.Root
import com.example.nasa_app.Repository.NasaRepository
import com.example.nasa_app.Service.NasaService
import com.example.nasa_app.Util.AppContant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class CuriosityViewModel(application: Application) : AndroidViewModel(application) {

    var getCuriosityphoto = MutableLiveData<Root>()
    var filterCameraOfCuriosity = MutableLiveData<Root>()

    suspend fun getCuriosityPhotoList(page: String?){
        withContext(Dispatchers.IO){
            var nasaService = Retrofit.Builder()
                            .baseUrl(AppContant.SERVİCE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                            .create(NasaService::class.java)
            NasaRepository(nasaService).getCuriosityPhotoList(page).apply {
                getCuriosityphoto.postValue(this.body())
            }
        }
    }

    suspend fun getFilterCameraOfCuriosity(camera: String?){
        withContext(Dispatchers.IO){
            var nasaService = Retrofit.Builder()
                    .baseUrl(AppContant.SERVİCE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NasaService::class.java)
            NasaRepository(nasaService).getFilterCameraOfCuriosity(camera).apply {
                filterCameraOfCuriosity.postValue(this.body())
            }
        }
    }
}