package com.arlequins.zoco_1.ui.tabsIndex.academics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AcademicsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is academics Fragment"
    }
    val text: LiveData<String> = _text
}