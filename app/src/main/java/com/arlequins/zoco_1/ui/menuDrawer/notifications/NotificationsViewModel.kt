package com.arlequins.zoco_1.ui.menuDrawer.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.NotificationRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.NotificationModel
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {

    private val notificationRepository = NotificationRepository()
    private var notificationList: ArrayList<NotificationModel> = ArrayList()
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _notificationsList: MutableLiveData<ArrayList<NotificationModel>> = MutableLiveData()
    val notificationsList: LiveData<ArrayList<NotificationModel>> = _notificationsList

    fun loadNotifications() {
        viewModelScope.launch {
            val result = notificationRepository.search()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents!!.forEach{ doc ->
                            val notification = doc.toObject<NotificationModel>()
                            if (notification !in notificationList)
                                notification?.let { notificationList.add(it) }
                        }
                        _notificationsList.postValue(notificationList)
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _showMsg.postValue(msg)
                    }
                    is ResourceRemote.Loading -> TODO()
                }
            }
        }
    }
}