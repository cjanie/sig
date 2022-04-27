package com.android.sig.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sig.R
import com.android.sig.ui.SharedViewModel
import com.android.sig.TypeEnum
import com.android.sig.businesslogic.exceptions.UndefinedTypeException

class NoteFragment: Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var pointName: TextView

    private lateinit var type: TextView

    private lateinit var note: EditText

    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_note, container, false)
        this.pointName = root.findViewById(R.id.note_point_name)
        this.type = root.findViewById(R.id.note_point_type)
        this.note = root.findViewById(R.id.note_edit)
        this.buttonSave = root.findViewById(R.id.button_save)

        val nameObserver = Observer<String> {newName ->
            this.pointName.text = newName
        }
        this.sharedViewModel.pointName.observe(this.viewLifecycleOwner, nameObserver)

        val typeObserver = Observer<TypeEnum> { newType ->
            if(newType != null) {
                this.type.text = newType.toString()
            }
        }
        this.sharedViewModel.type.observe(this.viewLifecycleOwner, typeObserver)

        this.buttonSave.setOnClickListener {
            this.recordNote()
            try {
                this.sharedViewModel.savePoint()
                this.sharedViewModel.reset()
                this.navigate(R.id.action_noteFragment_to_startFragment)
            } catch(e: Exception) {
                Toast.makeText(this.context, e.javaClass.simpleName, Toast.LENGTH_LONG).show()
                if (e is UndefinedTypeException)
                    this.navigate(R.id.action_noteFragment_to_typeFragment)
            }
        }

        return root
    }

    private fun recordNote() {
        if(!TextUtils.isEmpty(this.note.text)) {
            this.sharedViewModel.setNote(this.note.text.toString())
        }
    }

    private fun navigate(actionId: Int) {
        this.findNavController().navigate(actionId)
    }
}