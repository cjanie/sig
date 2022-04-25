package com.android.sig.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.android.sig.R
import com.android.sig.SavePointViewModel
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class StartFragment: Fragment() {

    private val sharedViewModel: SavePointViewModel by activityViewModels()

    private lateinit var point: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_start, container, false)
        this.point = root.findViewById(R.id.point)
        this.point.setOnClickListener(View.OnClickListener {

            this.handleClickPoint()
        })

        return root
    }

    @SuppressLint("MissingPermission")
    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if(isGranted) {
            // permission is granted
            val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(this.requireActivity())

            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if(location != null) {
                    this.recordPoint(location.longitude, location.latitude)
                    this.showToast(location.longitude, location.latitude)
                } else {
                    Toast.makeText(this.context, "Location is null", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            // permission is denied
            Toast.makeText(this.context, "Permission is denied", Toast.LENGTH_LONG).show()
        }
    }


    fun handleClickPoint() {
        this.activityResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        this.navigate()
    }

    private fun showToast(longitude: Double, latitude: Double) {
        Toast.makeText(
            this.context,
            this.getString(R.string.longitude_abbrev) +": " + longitude.toString()
                    + this.getString(R.string.latitude_abbrev)+ ": " + latitude.toString(),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun recordPoint(longitude: Double, latitude: Double) {
        this.sharedViewModel.setLongitude(longitude)
        this.sharedViewModel.setLatitude(latitude)
    }

    private fun navigate() {
        this.findNavController().navigate(R.id.action_startFragment_to_nameFragment)
    }


}