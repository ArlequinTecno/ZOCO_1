package com.arlequins.zoco_1.ui.details.detailsNotification


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ChatRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.model.Chat
import com.arlequins.zoco_1.model.NotificationModel
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class DetailNotificationViewModel : ViewModel() {
    private val chatRepository = ChatRepository()
    private val msgList: ArrayList<Chat> = ArrayList()
    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _chatList: MutableLiveData<ArrayList<Chat>> = MutableLiveData()
    val chatList: LiveData<ArrayList<Chat>> = _chatList
    private val _sentMsg: MutableLiveData<Boolean?> = MutableLiveData()
    val sentMsg: LiveData<Boolean?> = _sentMsg
    private val _sendMsg: MutableLiveData<Chat?> = MutableLiveData()
    val sendMsg: LiveData<Chat?> = _sendMsg

    fun loadMsg(notificationID: String?) {
        viewModelScope.launch{
            val result = chatRepository.loadMsg(notificationID)
            result.let{ resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents!!.forEach{doc ->
                            val msg = doc.toObject<Chat>()
                            if (msg !in msgList){
                                msg?.let { msgList.add(it) }
                            }


                        }
                        _chatList.postValue(msgList)
                    }
                    is ResourceRemote.Error -> {
                        _showMsg.postValue(result.message)
                    }
                    is ResourceRemote.Loading -> TODO()
                }

            }

        }
    }

    fun sendMsg(notification: NotificationModel, msg: String, date: String) {
        viewModelScope.launch{
            if (msg.isEmpty()){
                _sentMsg.postValue(false)
            }
            else{
                val chatMsg = Chat(
                    msg = msg,
                    date = date
                )
                val result = chatRepository.sendMsg(notification, chatMsg)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _sentMsg.postValue(true)
                        }
                        is ResourceRemote.Error ->{
                            _sentMsg.postValue(false)
                        }
                        is ResourceRemote.Loading -> TODO()
                    }
                }
            }

        }
    }
}