package com.example.nasa_app.Repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nasa_app.Model.Root
import com.example.nasa_app.Service.NasaService
import com.example.nasa_app.Util.AppContant
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NasaRepository constructor(var nasaService: NasaService) {

    suspend fun getCuriosityPhotoList(page: String?) = nasaService!!.getCuriosityPhotoList(AppContant.SOL_COUNT , page)

    suspend fun getOpportunityphotoList(page: String?) = nasaService!!.getOpportunityPhotoList(AppContant.SOL_COUNT , page)

    suspend fun getSpiritphotoList(page: String?) = nasaService!!.getSpiritPhotoList(AppContant.SOL_COUNT , page)

    suspend fun getFilterCameraOfCuriosity(camera: String?) = nasaService!!.filterCameraOfCuriosityPhoto(AppContant.SOL_COUNT , camera)

    suspend fun getfilterCameraOfOpportunityPhoto(camera: String?) = nasaService!!.filterCameraOfOpportunityPhoto(AppContant.SOL_COUNT , camera)

    suspend fun getfilterCameraOfSpiritPhoto(camera: String?) = nasaService!!.filterCameraOfSpiritPhoto(AppContant.SOL_COUNT , camera)

}

//    @SuppressLint("CheckResult")
//    suspend fun getCuriosityPhotoList(page: String?): MutableLiveData<Root> {
//        nasaService!!.getCuriosityPhotoList(AppContant.SOL_COUNT, page)
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribeOn(Schedulers.io())
//                ?.subscribe {
//                    object : Observer<Root> {
//                        override fun onSubscribe(d: Disposable) {}
//
//                        @SuppressLint("CheckResult")
//                        override fun onNext(root: Root) {
//                            Log.d("Response : " , "Geldi")
//                            getCuriosityphoto.postValue(root)
//                        }
//
//                        override fun onError(e: Throwable) {}
//                        override fun onComplete() {}
//                    }
//
//                }
//
//        return getCuriosityphoto


//        @SuppressLint("CheckResult")
//        suspend fun getOpportunityphotoList(page: String?): MutableLiveData<Root> {
//            nasaService!!.getOpportunityPhotoList(AppContant.SOL_COUNT, page)?.observeOn(AndroidSchedulers.mainThread())
//                    ?.subscribeOn(Schedulers.io())
//                    ?.subscribe {
//                        object : Observer<Root> {
//                            override fun onSubscribe(d: Disposable) {}
//                            override fun onNext(root: Root) {
//                                Log.d("Response : " , "Geldi")
//                                getOpportunityphoto.postValue(root)
//                            }
//
//                            override fun onError(e: Throwable) {}
//                            override fun onComplete() {}
//                        }
//
//                    }
//
//            return getOpportunityphoto
//        }
//            @SuppressLint("CheckResult")
//            suspend fun getSpiritphotoList(page: String?): MutableLiveData<Root> {
//                nasaService!!.getSpiritPhotoList(AppContant.SOL_COUNT, page)
//                        ?.observeOn(AndroidSchedulers.mainThread())
//                        ?.subscribeOn(Schedulers.io())
//                        ?.subscribe {
//                            object : Observer<Root> {
//                                override fun onSubscribe(d: Disposable) {}
//                                override fun onNext(root: Root) {
//                                    Log.d("Response : " , "Geldi")
//                                    getSpiritphoto.postValue(root)
//                                }
//
//                                override fun onError(e: Throwable) {}
//                                override fun onComplete() {}
//                            }
//                        }
//                return getSpiritphoto
//            }
//            @SuppressLint("CheckResult")
//            suspend fun getFilterCameraOfCuriosity(camera: String?): MutableLiveData<Root> {
//                nasaService!!.filterCameraOfCuriosityPhoto("1000", camera)
//                        ?.observeOn(AndroidSchedulers.mainThread())
//                        ?.subscribeOn(Schedulers.io())
//                        ?.subscribe {
//                            object : Observer<Root> {
//                                override fun onSubscribe(d: Disposable) {}
//                                override fun onNext(root: Root) {
//                                    Log.d("Response : " , "Geldi")
//                                    filterCameraOfCuriosity.postValue(root)
//                                }
//
//                                override fun onError(e: Throwable) {}
//                                override fun onComplete() {}
//                            }
//                        }
//                return filterCameraOfCuriosity
//            }
//            @SuppressLint("CheckResult")
//            suspend fun getFilterCameraOfOpportunity(camera: String?): MutableLiveData<Root> {
//                nasaService!!.filterCameraOfCuriosityPhoto("1000", camera)
//                        ?.observeOn(AndroidSchedulers.mainThread())
//                        ?.subscribeOn(Schedulers.io())?.subscribe {
//                            object : Observer<Root> {
//                                override fun onSubscribe(d: Disposable) {}
//                                override fun onNext(root: Root) {
//                                    Log.d("Response : " , "Geldi")
//                                    filterCameraOfOpportunity.postValue(root)
//                                }
//
//                                override fun onError(e: Throwable) {}
//                                override fun onComplete() {}
//                            }
//                        }
//                return filterCameraOfOpportunity
//            }
//
//            @SuppressLint("CheckResult")
//            suspend fun getFilterCameraOfSpirit(camera: String?): MutableLiveData<Root> {
//                nasaService!!.filterCameraOfCuriosityPhoto("1000", camera)
//                        ?.observeOn(AndroidSchedulers.mainThread())
//                        ?.subscribeOn(Schedulers.io())?.subscribe {
//                            object : Observer<Root> {
//                                override fun onSubscribe(d: Disposable) {}
//                                override fun onNext(root: Root) {
//                                    Log.d("Response : " , "Geldi")
//                                    filterCameraOfSpirit.postValue(root)
//                                }
//
//                                override fun onError(e: Throwable) {}
//                                override fun onComplete() {}
//                            }
//                        }
//                return filterCameraOfSpirit
//            }

//            companion object {
//                private var nasaRepositoryinstance: NasaRepository? = null
//                private var nasaService: NasaService? = null
//                @JvmStatic
//                val ınstance: NasaRepository?
//                    get() {
//                        if (nasaRepositoryinstance == null) {
//                            synchronized(NasaRepository::class.java) { nasaRepositoryinstance = NasaRepository() }
//                        }
//                        return nasaRepositoryinstance
//                    }
//
//                init {
//                    nasaService = Retrofit.Builder()
//                            .baseUrl(AppContant.SERVİCE_URL)
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .build()
//                            .create(NasaService::class.java)
//                }
//            }
