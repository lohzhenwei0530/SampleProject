package com.example.sampleproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.sampleproject.R
import com.example.sampleproject.framework.base.BaseFragment
import com.example.sampleproject.framework.presenter.GoogleMapPresenter
import com.example.sampleproject.framework.presenter.LoginPresenter
import com.example.sampleproject.framework.view.GoogleMapView
import com.example.sampleproject.framework.view.LoginView
import com.example.sampleproject.model.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_google_map.*
import kotlinx.android.synthetic.main.fragment_login.*

class GoogleMapFragment : BaseFragment(), GoogleMapView, OnMapReadyCallback {

    lateinit var presenter: GoogleMapPresenter

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_google_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = GoogleMapPresenter(this)
        presenter.bindView(this)
        setTitle("Google Map")
        initView()
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onMapReady(p0: GoogleMap?) {
        val latLng= LatLng(mainViewModel.user.address.geo.lat.toDouble(),mainViewModel.user.address.geo.lng.toDouble())
        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .zoom(3f)
            .bearing(0f)
            .tilt(45f)
            .build()
        p0?.apply {
            this.mapType = GoogleMap.MAP_TYPE_NORMAL
            this.addMarker(
                MarkerOptions().position(latLng)
                    .title(mainViewModel.user.name)
            )
            this.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

    }

    private fun initView() {
        map.onCreate(null)
        map.onResume()
        map.getMapAsync(this)
    }

}