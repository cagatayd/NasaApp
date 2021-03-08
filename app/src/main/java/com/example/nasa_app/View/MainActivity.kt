package com.example.nasa_app.View

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.appbar.AppBarLayout
import androidx.viewpager.widget.ViewPager
import com.example.nasa_app.ViewModel.CuriosityViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.nasa_app.Adapter.ViewPagerAdapter
import android.os.Bundle
import android.view.Menu
import com.example.nasa_app.R
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private val appBarLayout: AppBarLayout? = null
    private var viewPager: ViewPager? = null
    var curiosityViewModel: CuriosityViewModel? = null
    private val dialogBuilder: MaterialAlertDialogBuilder? = null
    private val cancel: Button? = null
    var camera: String? = null
    var adapter = ViewPagerAdapter(supportFragmentManager)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tablayout_id)
        viewPager = findViewById(R.id.viewpager_id)
        adapter.AddFragment(BlankFragmentCuriosty(), "Curiosty")
        adapter.AddFragment(BlankFragmentOppurttunity(), "Oppurttunity")
        adapter.AddFragment(BlankFragmentSpirit(), "Spirit")

        viewPager?.adapter =adapter
        tabLayout?.setupWithViewPager(viewPager)
        curiosityViewModel = ViewModelProvider(viewModelStore,
                AndroidViewModelFactory(this.application)).get(CuriosityViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}