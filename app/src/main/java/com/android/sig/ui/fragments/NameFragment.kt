package com.android.sig.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sig.Launch
import com.android.sig.R
import com.android.sig.viewmodels.SharedViewModel

class NameFragment: Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var longitude: TextView

    private lateinit var latitude: TextView

    private lateinit var pointName: EditText

    private lateinit var buttonNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_name, container, false)
        this.longitude = root.findViewById(R.id.name_longitude)
        this.latitude = root.findViewById(R.id.name_latitude)
        this.pointName = root.findViewById(R.id.point_name)
        this.buttonNext = root.findViewById(R.id.button_name_next)

        val longitudeObserver: Observer<Double> = Observer { newLongitude ->
            if(newLongitude != null) {
                this.longitude.text = newLongitude.toString()
            }
        }
        this.sharedViewModel.longitude.observe(this.viewLifecycleOwner, longitudeObserver)

        val latitudeObserver: Observer<Double> = Observer { newLatitude ->
            if(newLatitude != null) {
                this.latitude.text = newLatitude.toString()
            }
        }
        this.sharedViewModel.latitude.observe(this.viewLifecycleOwner, latitudeObserver)

        val pointNameObserver: Observer<String> = Observer { pointNameResult ->
            this.pointName.text.contentEquals(pointNameResult)
        }
        this.sharedViewModel.pointName.observe(this.viewLifecycleOwner, pointNameObserver)

        this.buttonNext.setOnClickListener {
            this.handleClickNext()
        }

        return root
    }

    private fun handleClickNext() {
        this.recordName()
        this.navigate()
    }

    private fun recordName() {
        this.sharedViewModel.setPointName(this.pointName.text.toString())
    }

    private fun navigate() {
        this.findNavController().navigate(R.id.action_nameFragment_to_typeFragment)
    }


}