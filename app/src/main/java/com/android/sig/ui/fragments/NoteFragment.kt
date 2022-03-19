package com.android.sig.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.sig.R
import com.android.sig.RecordViewModel

class NoteFragment: Fragment() {

    val sharedViewModel: RecordViewModel by activityViewModels()

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
        return root
    }
}