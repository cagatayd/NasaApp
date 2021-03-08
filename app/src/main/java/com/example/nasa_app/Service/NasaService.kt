package com.example.nasa_app.Service

import com.example.nasa_app.Model.Root
import com.example.nasa_app.Util.AppContant
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET(AppContant.Get_Curiosity + AppContant.API_KEY)
    suspend fun getCuriosityPhotoList(@Query("sol") sol: String?, @Query("page") page: String?): Response<Root?>

    @GET(AppContant.Get_Opportunity + AppContant.API_KEY)
    suspend fun getOpportunityPhotoList(@Query("sol") sol: String?, @Query("page") page: String?): Response<Root?>

    @GET(AppContant.Get_Spirit + AppContant.API_KEY)
    suspend fun getSpiritPhotoList(@Query("sol") sol: String?, @Query("page") page: String?): Response<Root?>

    @GET(AppContant.Get_Curiosity + AppContant.API_KEY)
    suspend fun filterCameraOfCuriosityPhoto(@Query("sol") sol: String?, @Query("camera") camera: String?): Response<Root?>

    @GET(AppContant.Get_Curiosity + AppContant.API_KEY)
    suspend fun filterCameraOfOpportunityPhoto(@Query("sol") sol: String?, @Query("camera") camera: String?): Response<Root?>

    @GET(AppContant.Get_Curiosity + AppContant.API_KEY)
    suspend fun filterCameraOfSpiritPhoto(@Query("sol") sol: String?, @Query("camera") camera: String?): Response<Root?>
}