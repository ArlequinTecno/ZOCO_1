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

    private val _chatListM: MutableLiveData<ArrayList<Chat>> = MutableLiveData()
    val chatListM: LiveData<ArrayList<Chat>> = _chatListM
    private val _sentMsg: MutableLiveData<Chat?> = MutableLiveData()
    val sentMsg: LiveData<Chat?> = _sentMsg
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
                        _chatListM.postValue(msgList)
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
                _showMsg.postValue("Escribe algo")
            }
            else{
                val chat = Chat(
                    msg = msg,
                    date = date
                )
                val result = chatRepository.sendMsg(notification, chat)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _sentMsg.postValue(result.data)
                        }
                        is ResourceRemote.Error ->{
                            _showMsg.postValue("Error al enviar mensaje")
                        }
                        is ResourceRemote.Loading -> TODO()
                    }
                }
            }

        }
    }
}