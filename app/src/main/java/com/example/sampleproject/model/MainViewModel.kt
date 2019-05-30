package com.example.sampleproject.model

import androidx.lifecycle.ViewModel
import com.example.sampleproject.network.model.User

class MainViewModel : ViewModel() {
    var user = User()
}