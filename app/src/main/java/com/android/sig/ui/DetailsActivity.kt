package com.android.sig.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.sig.R

class DetailsActivity: BaseActivity() {

    private lateinit var image: ImageView

    private lateinit var pointName: TextView

    private lateinit var type: TextView

    private lateinit var note: TextView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this.setContentView(R.layout.activity_details)

        this.image = this.findViewById(R.id.details_image)
        this.pointName = this.findViewById(R.id.details_point_name)
        this.type = this.findViewById(R.id.details_type)
        this.note = this.findViewById(R.id.details_note)

        // Data
        this.image.setImageResource(this.intent.getIntExtra("icon", R.drawable.default_icon))
        this.pointName.text = this.intent.getStringExtra("pointName")
        this.type.text = this.intent.getStringExtra("type")
        this.note.text = this.intent.getStringExtra("note")

        Toast.makeText(this, "Details", Toast.LENGTH_LONG).show()
        System.out.println("Details %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
    }


}