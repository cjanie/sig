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
import com.android.sig.R
import com.android.sig.viewmodels.SharedViewModel
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.sig.ui.enums.SavePointActionResponseEnum
import com.android.sig.ui.enums.SavePointActionResponseVisitor
import com.android.sig.utils.MessageHandler

class NoteFragment: Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var pointName: TextView

    private lateinit var type: TextView

    private lateinit var note: EditText

    private lateinit var buttonSave: Button

    private val messageHandler = MessageHandler()

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

        // Listening to the results of the view model
        val nameObserver = Observer<String> {nameResult ->
            if(!nameResult.isNullOrEmpty())
                this.pointName.text = nameResult
        }
        this.sharedViewModel.pointName.observe(this.viewLifecycleOwner, nameObserver)

        val typeObserver = Observer<TypeEnum> { newType ->
            if(newType != null)
                this.type.text = newType.toString()
        }
        this.sharedViewModel.type.observe(this.viewLifecycleOwner, typeObserver)

        val noteObserver: Observer<String> = Observer { noteResult ->
            if(!noteResult.isNullOrEmpty())
                this.note.text.append(noteResult)
        }
        this.sharedViewModel.note.observe(this.viewLifecycleOwner, noteObserver)

        val savePointActionResponseObserver = Observer<SavePointActionResponseEnum> { actionResult ->
            if(actionResult != null)
                this.handleSavePointActionResponse(actionResult)
        }
        this.sharedViewModel.savePointActionResponse.observe(this.viewLifecycleOwner, savePointActionResponseObserver)

        // Click listener call to view model action
        this.buttonSave.setOnClickListener {
            this.recordNote()
            this.savePoint()
        }

        return root
    }

    // View model actions
    private fun recordNote() {
        if(!TextUtils.isEmpty(this.note.text)) {
            this.sharedViewModel.setNote(this.note.text.toString())
        }
    }

    private fun savePoint() {
        sharedViewModel.savePoint()
    }

    // Visitor pattern to handle save point action response
    private fun handleSavePointActionResponse(savePointActionResponseEnum: SavePointActionResponseEnum) {
        val visitor = object: SavePointActionResponseVisitor {

            override fun visitSuccess() {
                sharedViewModel.reset()
                navigate(R.id.action_noteFragment_to_startFragment)
            }

            override fun visitMissingTypeError() {
                navigate(R.id.action_noteFragment_to_typeFragment)
            }

            override fun visitNoAvailableGeolocationError() {
                navigate(R.id.action_noteFragment_to_startFragment)
            }
        }

        savePointActionResponseEnum.accept(visitor)
        showToast(savePointActionResponseEnum.toString())

    }

    // Nav
    private fun navigate(actionId: Int) {
        this.findNavController().navigate(actionId)
    }

    private fun showToast(message: String) {
        this.messageHandler.showToast(this.requireContext(), message)
    }


}