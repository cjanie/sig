package com.android.sig.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.android.sig.Launch
import com.android.sig.R
import com.android.businesslogic.domain.entities.Point
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
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity: BaseActivity(), OnMapReadyCallback {

    // MVVM
    private val mapViewModel: MapViewModel by viewModels {
        (this.application as Launch).mapViewModelFactory
    }

    // Google map UI
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


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
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        this.mMap = googleMap

        try {
            this.mMap.isMyLocationEnabled = true
        } catch (e: Exception) {
            Toast.makeText(this, e.javaClass.simpleName + " " + this.getText(R.string.request_location_permission), Toast.LENGTH_LONG).show()
        }

        // Listens to the view model result of the view model action
        val pointsObserver: Observer<List<Point>> = Observer { pointsResults ->
            this.updateMapWithMarkers(pointsResults)
            if(!pointsResults.isEmpty())
                this.moveCameraToPoint(pointsResults.get(pointsResults.size - 1))
        }
        this.mapViewModel.points.observe(this, pointsObserver)
    }

    private fun updateMapWithMarkers(availablePoints: List<Point>) {
        if(!availablePoints.isNullOrEmpty()) {
            for(point in availablePoints) {
                this.mMap.addMarker(this.createMarkerOptions(point))
            }
        }
    }

    private fun createMarkerOptions(point: Point): MarkerOptions {
        val drawable: Drawable = this.getDrawable(
            TypeIconHandler().getIconId(point.type!!)
        ) as Drawable
        val smallBitmap: Bitmap = ImageTransformer().getSmallerImage(drawable, 2)

        val latLong = LatLng(point.latitude, point.longitude)

        return MarkerOptions()
            .position(latLong)
            .title(point.name)
            .icon(BitmapDescriptorFactory.fromBitmap(smallBitmap))
            .snippet(point.note)
    }

    private fun moveCameraToPoint(point: Point) {
            this.mMap.moveCamera(CameraUpdateFactory.newLatLng(
                LatLng(point.latitude, point.longitude)
            ))
    }

}