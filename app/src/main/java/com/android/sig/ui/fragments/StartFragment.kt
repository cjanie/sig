package com.android.sig.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.android.sig.R
import com.android.sig.viewmodels.SharedViewModel
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sig.Launch
import com.android.sig.ui.MainActivity
import com.google.android.gms.location.*

class StartFragment: Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels {
            (this.activity?.application as Launch).sharedViewModelFactory
    }

    private lateinit var point: ImageView

    private val locationLiveData: MutableLiveData<Location> = MutableLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_start, container, false)
        this.point = root.findViewById(R.id.point)
        this.point.setOnClickListener {
            this.handleClickPoint()
        }
        return root
    }

    @SuppressLint("MissingPermission")
    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { permissionIsGranted ->
        if(permissionIsGranted) {

            val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(this.requireActivity())

            fusedLocationProviderClient.locationAvailability.addOnSuccessListener { locationAvailability ->

                val locationCallBack = object: LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        if(!locationResult.equals(null) && locationResult.locations.isNotEmpty()) {
                            stopLocationUpdates(fusedLocationProviderClient, this)
                            val newLocation = locationResult.locations[0]
                            saveLocation(newLocation)
                            showLocationSuccessMessage(newLocation)
                        }
                    }
                }

                if(locationAvailability.isLocationAvailable) {
                    fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                        val lastLocation: Location = task.result
                        this.saveLocation(lastLocation)
                        this.navigateToNextStep()
                    }

                } else {
                    this.showMessage(this.getString(R.string.location_not_available))
                    this.requestLocationUpdates(
                        fusedLocationProviderClient, locationCallBack
                    )
                }
            }

        } else {
            this.showMessage("Permission is denied")
        }
    }


    private fun handleClickPoint() {
        this.activityResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun saveLocation(location: Location) {
        this.sharedViewModel.setLatitude(location.latitude)
        this.sharedViewModel.setLongitude(location.longitude)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(
        fusedLocationProviderClient: FusedLocationProviderClient,
        locationCallback: LocationCallback
    ) {
        val locationRequest = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.getMainLooper()
        )
    }

    private fun stopLocationUpdates(
        fusedLocationProviderClient: FusedLocationProviderClient,
        locationCallback: LocationCallback
    ) {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun showLocationSuccessMessage(location: Location) {
        val message = this.getString(R.string.latitude_abbrev)+ ": " + location.latitude.toString() + " " +
                this.getString(R.string.longitude_abbrev) +": " + location.longitude.toString()
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToNextStep() {
        this.findNavController().navigate(R.id.action_startFragment_to_nameFragment)
    }

}