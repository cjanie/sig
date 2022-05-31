package com.android.sig.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sig.R
import com.android.sig.viewmodels.SharedViewModel
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.domain.enums.TypeVisitor
import com.android.sig.ui.enums.SavePointActionResponseEnum
import com.android.sig.ui.enums.SavePointActionResponseVisitor
import com.android.sig.utils.MessageHandler

class TypeFragment: Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var types: RadioGroup

    private lateinit var pointName: TextView

    private lateinit var buttonAddNote: Button

    private lateinit var buttonSave: Button

    private val messageHandler = MessageHandler()

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

        this.types.setOnCheckedChangeListener { radioGroup, _ ->

            // Call to view model actions

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

            this.sharedViewModel.resetSavePointActionResponse()
        }

        // Listening to the view model results of actions
        val typeObserver = Observer<TypeEnum> { typeResult ->
            if(typeResult != null)
                this.types.check(this.getRadioButtonId(typeResult))
        }
        this.sharedViewModel.type.observe(this.viewLifecycleOwner, typeObserver)

        val nameObserver = Observer<String> { nameResult ->
            this.pointName.text = nameResult
        }
        this.sharedViewModel.pointName.observe(this.viewLifecycleOwner, nameObserver)

        // Listening to the view model result of the save point action
        val savePointActionResponseObserver = Observer<SavePointActionResponseEnum> { actionResponseResult ->
            if(actionResponseResult != null) {
                this.handleSavePointActionResponse(actionResponseResult)
            }
        }
        this.sharedViewModel.savePointActionResponse.observe(this.viewLifecycleOwner, savePointActionResponseObserver)

        // Click listeners to call action of the view model
        this.buttonSave.setOnClickListener {
            this.sharedViewModel.savePoint()
        }

        this.buttonAddNote.setOnClickListener {
            this.navigate(R.id.action_typeFragment_to_noteFragment)
        }

      return root
    }

    // Visitor pattern to update radio button checked
    private fun getRadioButtonId(type: TypeEnum): Int {

        val visitor = object : TypeVisitor<Int> {

            override fun visitRuin(): Int {
                return R.id.ruin
            }

            override fun visitCastel(): Int {
                return R.id.castel
            }

            override fun visitWall(): Int {
                return R.id.wall
            }

            override fun visitHistoricSite(): Int {
                return R.id.historic
            }

            override fun visitArchaeologicSite(): Int {
                return R.id.archaeologic
            }

            override fun visitOtherType(): Int {
                return R.id.other_type
            }
        }

        return type.accept(visitor)
    }

    // Visitor pattern to handle save point action response
    private fun handleSavePointActionResponse(savePointActionResponseEnum: SavePointActionResponseEnum) {

        val visitor = object: SavePointActionResponseVisitor {

            override fun visitSuccess() {
                sharedViewModel.reset()
                navigate(R.id.action_typeFragment_to_startFragment)
                showToast(savePointActionResponseEnum.toString())
            }

            override fun visitMissingTypeError() {
                showToast(savePointActionResponseEnum.toString())
            }

            override fun visitNoAvailableGeolocationError() {
                navigate(R.id.action_typeFragment_to_startFragment)
            }
        }

        savePointActionResponseEnum.accept(visitor)
    }

    // Nav
    private fun navigate(actionId: Int) {
        this.findNavController().navigate(actionId)
    }

    private fun showToast(message: String) {
        this.messageHandler.showToast(this.requireContext(), message)
    }
}