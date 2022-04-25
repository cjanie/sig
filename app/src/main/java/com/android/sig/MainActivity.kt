package com.android.sig


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.android.sig.ui.MapActivity
import com.google.android.material.navigation.NavigationView
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        val toolbar: Toolbar = this.findViewById(R.id.toolbar)
        this.setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = this.findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = this.findViewById(R.id.nav_view)

        toolbar.setNavigationOnClickListener { view ->
            drawerLayout.open()
        }

        navigationView.setCheckedItem(R.id.menu_item_point)
        navigationView.setNavigationItemSelectedListener { item ->
            if(item.itemId == R.id.menu_item_map) {
                navigateToMapActivity()
            }
            drawerLayout.close()
            false // corresponds to is checked = true
        }

        this.blurBackground();
    }

    private fun blurBackground() {
        val blurView: BlurView = this.findViewById(R.id.blur_layout)
        val viewGroup: ViewGroup = this.findViewById(R.id.background)
        blurView.setupWith(viewGroup)
            .setBlurAlgorithm(RenderScriptBlur(this));
    }

    private fun navigateToMapActivity() {
        val intent = Intent(this, MapActivity::class.java)
        this.startActivity(intent)
    }
}