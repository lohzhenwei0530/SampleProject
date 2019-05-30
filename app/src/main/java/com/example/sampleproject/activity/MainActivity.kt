package com.example.sampleproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sampleproject.R
import kotlinx.android.synthetic.main.view_loading_progress.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController=findNavController(R.id.nav_container)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_container).navigateUp()

    fun setToolBarTitle(title: String) {
        this.title = title
    }

    fun showLoadingProgress(){
        fl_loading.visibility= View.VISIBLE
    }

    fun hideLoadingProgress(){
        fl_loading.visibility= View.GONE
    }
}
