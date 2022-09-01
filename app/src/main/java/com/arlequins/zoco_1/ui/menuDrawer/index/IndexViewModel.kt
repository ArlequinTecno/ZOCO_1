package com.arlequins.zoco_1.ui.menuDrawer.index

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IndexViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Index Fragment"
    }
    val text: LiveData<String> = _text
}