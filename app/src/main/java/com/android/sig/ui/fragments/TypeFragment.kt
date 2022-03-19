package com.android.sig.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.annotation.IntegerRes
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.sig.R
import com.android.sig.RecordViewModel

class TypeFragment: Fragment(), View.OnClickListener {

    val sharedViewModel: RecordViewModel by activityViewModels()

    private lateinit var types: RadioGroup

    private lateinit var buttonAddNote: Button

    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_type, container, false)
        this.types = root.findViewById(R.id.type_radioGroup)
        this.buttonAddNote = root.findViewById(R.id.button_type_add_note)
        this.buttonSave = root.findViewById(R.id.button_type_save)

        this.types.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            val checkedRadioButtonId: Int = radioGroup.checkedRadioButtonId
            if(checkedRadioButtonId == R.id.ruin) {
                this.sharedViewModel.setType(getString(R.string.ruin))
            }
            if(checkedRadioButtonId == R.id.castel) {
                this.sharedViewModel.setType(getString(R.string.castel))
            }
            if(checkedRadioButtonId == R.id.wall) {
                this.sharedViewModel.setType(getString(R.string.wall))
            }
            if(checkedRadioButtonId == R.id.archaeologic) {
                this.sharedViewModel.setType(getString(R.string.archeologic_site))
            }
            if(checkedRadioButtonId == R.id.historic) {
                this.sharedViewModel.setType(getString(R.string.historic_site))
            }
            if(checkedRadioButtonId == R.id.other_type) {
                this.sharedViewModel.setType(getString(R.string.other_type))
            }
        })
        return root
    }

    override fun onClick(p0: View?) {
        if(p0 == this.buttonAddNote) {

        }
        if(p0 == this.buttonSave) {

        }
    }


}