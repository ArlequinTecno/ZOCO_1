package com.arlequins.zoco_1.ui.menuDrawer.aboutZoco

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutZocoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is About Zoco Fragment"
    }
    val text: LiveData<String> = _text
}