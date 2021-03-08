package com.example.nasa_app.View

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasa_app.Adapter.MainAdapter
import com.example.nasa_app.Listener.OnNasaItemClickListener
import com.example.nasa_app.Listener.PaginationsScrollListener
import com.example.nasa_app.Model.Photo
import com.example.nasa_app.R
import com.example.nasa_app.ViewModel.CuriosityViewModel
import com.example.nasa_app.ViewModel.OpportunityViewModel
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.ArrayList

class BlankFragmentOppurttunity : Fragment(),OnNasaItemClickListener{

    var recyclerView: RecyclerView? = null
    var progressBarOpportunity: ProgressBar? = null
    var gridLayoutManager: GridLayoutManager? = null
    var opportunityViewModel: OpportunityViewModel? = null
    private val PAGE_START = 1
    private val isLoading = false
    private val isLastPage = false
    private var currentPage = PAGE_START
    private var camera: String? = null
    private var dialogBuilder: AlertDialog.Builder? = null
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blank_oppurttunity, container, false)
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        opportunityViewModel = ViewModelProvider(viewModelStore,
                ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(OpportunityViewModel::class.java)

        showProgressBar()
        getPhotos(currentPage)
        initListeners()

    }

    private fun initListeners(){
        recyclerView!!.addOnScrollListener(object : PaginationsScrollListener(gridLayoutManager!!) {
            override var isLoading: Boolean = false
            override val isLastPage: Boolean = false
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                showProgressBar()
                getPhotos(currentPage)
            }
        })
    }
    private fun init() {
        recyclerView = view!!.findViewById(R.id.recyclerView_opportunity)
        gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager!!.orientation = LinearLayoutManager.VERTICAL
        progressBarOpportunity = view!!.findViewById(R.id.progressBarOpportunity)
    }

    private fun loadroversphotos(photoList: List<Photo?>) {

        Thread(Runnable {
            requireActivity().runOnUiThread(java.lang.Runnable {
                val mainAdapter = MainAdapter(photoList as ArrayList<Photo?>, requireActivity().applicationContext, this)
                recyclerView!!.itemAnimator = DefaultItemAnimator()
                recyclerView!!.layoutManager = gridLayoutManager
                recyclerView!!.adapter = mainAdapter
                mainAdapter.notifyDataSetChanged()
            })
        }).start()

    }


    private fun getPhotos(currentPage: Int){
        CoroutineScope(Dispatchers.IO).launch {
            opportunityViewModel!!.getOpportunityPhotoList(currentPage.toString())
            if (opportunityViewModel!!.getOpportunityyphoto.value != null){
                loadroversphotos(opportunityViewModel!!.getOpportunityyphoto.value!!.photos)
            }
            hideProgressBar();
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.allcamera -> {
                getPhotos(currentPage)
            }

            R.id.itemFHAZ -> {
                camera = "FHAZ"
            }

            R.id.itemRHAZ -> {
                camera = "RHAZ"
            }

            R.id.MAST -> {
                camera = "MAST"
            }

            R.id.CHEMCAM -> {
                camera = "CHEMCAM"
            }

            R.id.MAHLI -> {
                camera = "MAHLI"
            }

            R.id.MARDI -> {
                camera = "MARDI"
            }

            R.id.NAVCAM -> {
                camera = "NAVCAM"
            }

            R.id.PANCAM -> {
                camera = "PANCAM"
            }

            R.id.MINITES -> {
                camera = "MINITES"
            }

        }
        if (camera!=null){
            CoroutineScope(Dispatchers.IO).launch {
                opportunityViewModel!!.getFilterCameraOfOpportunityy(camera)
                loadroversphotos(opportunityViewModel!!.filterCameraOfOpportunity.value!!.photos);
                hideProgressBar();
            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun showProgressBar() {
        Thread(Runnable {
            requireActivity().runOnUiThread(java.lang.Runnable {
                progressBarOpportunity!!.visibility = View.VISIBLE
            })
        }).start()
    }


    fun hideProgressBar() {
        Thread(Runnable {
            requireActivity().runOnUiThread(java.lang.Runnable {
                if (progressBarOpportunity != null && progressBarOpportunity!!.isShown) {
                    progressBarOpportunity!!.visibility = View.GONE
                }
            })
        }).start()
    }

    override fun onClick(photo: Photo?) {
        dialogBuilder = AlertDialog.Builder(requireContext())
        val popUpView = requireActivity().layoutInflater.inflate(R.layout.detail_popup, null)
        val textViewRoverName = popUpView.findViewById<TextView>(R.id.RoverName)
        val textViewDateTaken = popUpView.findViewById<TextView>(R.id.datetaken)
        val textViewCameraName = popUpView.findViewById<TextView>(R.id.CameraName)
        val textViewStatus = popUpView.findViewById<TextView>(R.id.Status)
        val textViewLaunchDate = popUpView.findViewById<TextView>(R.id.launchdate)
        val textViewLandingDate = popUpView.findViewById<TextView>(R.id.landingdate)
        val imageViewRoverPhoto = popUpView.findViewById<ImageView>(R.id.roverphoto)

        textViewRoverName.text = photo!!.rover.name
        textViewDateTaken.text = photo.earth_date
        textViewCameraName.text = photo.camera.name
        textViewStatus.text = photo.rover.status
        textViewLaunchDate.text = photo.rover.launch_date
        textViewLandingDate.text = photo.rover.landing_date
        Glide.with(requireContext()).load(photo.img_src).into(imageViewRoverPhoto)

        dialogBuilder!!.setView(popUpView)
        dialog = dialogBuilder!!.create()
        dialog!!.show()
    }
    }

