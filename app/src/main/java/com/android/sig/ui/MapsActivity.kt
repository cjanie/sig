package com.android.sig.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.android.sig.Launch
import com.android.sig.R
import com.android.sig.businesslogic.entities.Point
import com.android.sig.databinding.ActivityMapsBinding
import com.google.android.gms.dynamic.IObjectWrapper

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val mapViewModel: MapViewModel by viewModels {
        (this.application as Launch).mapViewModelFactory()
    }
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
                    val bitmapDrawable: BitmapDrawable = this.getDrawable(
                        TypeIconHandler().getIconId(point.type!!)
                    ) as BitmapDrawable
                    val bitmap: Bitmap = bitmapDrawable.bitmap
                    val smallBitmap: Bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width/2, bitmap.height/2, false)

                    val latLong = LatLng(point.latitude, point.longitude)
                    mMap.addMarker(
                        MarkerOptions()
                            .position(latLong)
                            .title(point.name)
                            .icon(BitmapDescriptorFactory.fromBitmap(smallBitmap))
                    )
                }
            }
        }
        this.mapViewModel.points.observe(this, pointsObserver)
    }
}