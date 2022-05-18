package com.android.sig.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.android.sig.Launch
import com.android.sig.R
import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.sig.databinding.ActivityMapsBinding
import com.android.sig.utils.ImageTransformer
import com.android.sig.utils.TypeIconHandler
import com.android.sig.viewmodels.MapViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity: BaseActivity(), OnMapReadyCallback {

    private val TAG = "MAPS ACTIVITY"

    private val mapViewModel: MapViewModel by viewModels {
        (this.application as Launch).mapViewModelFactory
    }
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var marker: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Piolenc, Vaucluse.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Piolenc and move the camera
        val piolenc = LatLng(44.178621, 4.7628292)
        mMap.addMarker(MarkerOptions().position(piolenc).title("Piolenc"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(piolenc))

        val pointsObserver: Observer<List<Point>> = Observer { newPoints ->
            if(!newPoints.isNullOrEmpty()) {
                for(point in newPoints) {
                    val drawable: Drawable = this.getDrawable(
                        TypeIconHandler().getIconId(point.type!!)
                    ) as Drawable
                    val smallBitmap: Bitmap = ImageTransformer().getSmallerImage(drawable, 2)
                    val latLong = LatLng(point.latitude, point.longitude)

                    mMap.addMarker(
                        MarkerOptions()
                            .position(latLong)
                            .title(point.name)
                            .icon(BitmapDescriptorFactory.fromBitmap(smallBitmap))
                            .snippet(point.note)
                    )
                }
            }
        }
        this.mapViewModel.points.observe(this, pointsObserver)
    }

}