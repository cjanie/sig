package com.android.sig.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sig.R
import com.android.sig.SavePointViewModel
import com.android.sig.TypeEnum
import com.android.sig.businesslogic.exceptions.NoAvailableGeolocationException

class TypeFragment: Fragment() {

    val sharedViewModel: SavePointViewModel by activityViewModels()

    private lateinit var types: RadioGroup

    private lateinit var pointName: TextView

    private lateinit var buttonAddNote: Button

    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_type, container, false)
        this.types = root.findViewById(R.id.type_radioGroup)
        this.pointName = root.findViewById(R.id.type_point_name)
        this.buttonAddNote = root.findViewById(R.id.button_type_add_note)
        this.buttonSave = root.findViewById(R.id.button_type_save)

        this.types.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            val checkedRadioButtonId: Int = radioGroup.checkedRadioButtonId
            if(checkedRadioButtonId == R.id.ruin) {
                this.sharedViewModel.setType(TypeEnum.RUIN)
            }
            if(checkedRadioButtonId == R.id.castel) {
                this.sharedViewModel.setType(TypeEnum.CASTEL)
            }
            if(checkedRadioButtonId == R.id.wall) {
                this.sharedViewModel.setType(TypeEnum.WALL)
            }
            if(checkedRadioButtonId == R.id.archaeologic) {
                this.sharedViewModel.setType(TypeEnum.ARCHEOLOGIC_SITE)
            }
            if(checkedRadioButtonId == R.id.historic) {
                this.sharedViewModel.setType(TypeEnum.HISTORIC_SITE)
            }
            if(checkedRadioButtonId == R.id.other_type) {
                this.sharedViewModel.setType(TypeEnum.OTHER_TYPE)
            }
        })

        val nameObserver = Observer<String> {newName ->
            this.pointName.text = newName
        }
        this.sharedViewModel.pointName.observe(this.viewLifecycleOwner, nameObserver)

        this.buttonAddNote.setOnClickListener(View.OnClickListener {
            this.navigate(R.id.action_typeFragment_to_noteFragment)
        })

        this.buttonSave.setOnClickListener(View.OnClickListener {

            try {
                this.sharedViewModel.savePoint()
                this.sharedViewModel.resetRecord()
                this.navigate(R.id.action_typeFragment_to_startFragment)
            } catch (e: NoAvailableGeolocationException) {
                Toast.makeText(this.context, "Save: " + e.javaClass.name, Toast.LENGTH_LONG).show()
            }

        })
        return root
    }

    private fun navigate(actionId: Int) {
        this.findNavController().navigate(actionId)
    }
}