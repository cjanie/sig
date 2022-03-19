package com.android.sig.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.sig.R
import com.android.sig.RecordViewModel

class NameFragment: Fragment() {

    private val sharedViewModel: RecordViewModel by activityViewModels()

    private lateinit var pointName: EditText

    private lateinit var buttonNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_name, container, false)
        this.pointName = root.findViewById(R.id.point_name)
        this.buttonNext = root.findViewById(R.id.button_name_next)

        if(this.sharedViewModel.hasNoNameSet()) {
            this.sharedViewModel.setPointName(getString(R.string.point_name))
        }
        return root
    }
}