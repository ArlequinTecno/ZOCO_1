package com.arlequins.zoco_1.ui.menuDrawer.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is category Fragment"
    }
    val text: LiveData<String> = _text
}