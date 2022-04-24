package com.android.sig.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.sig.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mapView: MapView;

    val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_map)

        this.mapView = this.findViewById(R.id.map_view)

        var mapViewBundle: Bundle? = null;
        if(savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(this.MAPVIEW_BUNDLE_KEY)!!
        }


        this.mapView.onCreate(mapViewBundle);
        this.mapView.getMapAsync(this);
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        var mapViewBundle: Bundle? = outState.getBundle(this.MAPVIEW_BUNDLE_KEY)
        if(mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(this.MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        this.mapView.onSaveInstanceState(mapViewBundle);
    }

    override fun onResume() {
        super.onResume()
        this.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        this.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        this.mapView.onStop()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.isMyLocationEnabled
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
        this.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        this.mapView.onLowMemory()
    }


}