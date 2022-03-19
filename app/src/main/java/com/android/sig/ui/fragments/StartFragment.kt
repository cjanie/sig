package com.android.sig.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.sig.R
import com.android.sig.RecordViewModel
import androidx.fragment.app.activityViewModels

class StartFragment: Fragment() {

    private val sharedViewModel: RecordViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_start, container, false)



        return root
    }
}