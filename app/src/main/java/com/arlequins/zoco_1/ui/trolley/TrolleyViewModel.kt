package com.arlequins.zoco_1.ui.trolley

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.NotificationRepository
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.UserRepository
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.Chat
import com.arlequins.zoco_1.model.NotificationModel
import com.arlequins.zoco_1.model.User
import kotlinx.coroutines.launch

class TrolleyViewModel : ViewModel() {
    private val _total: MutableLiveData<String?> = MutableLiveData()
    val total: LiveData<String?> = _total

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _createNotification: MutableLiveData<String?> = MutableLiveData()
    val createNotification: LiveData<String?> = _createNotification

    private val notificationRepository = NotificationRepository()
    private val userRepository = UserRepository()

    fun calculateTotal(number: String, price: String?) {
        if (number != "") {
            val num = number.toInt()
            val priceInt = price!!.toInt()
            val totalInt = num * priceInt
            Log.i("total", totalInt.toString())
            _total.postValue(totalInt.toString())

        } else{
            _total.postValue(price)
        }
    }

    fun validateFields(
        article: Article,
        numArticle: String,
        msgClient: String,
        total: String,
        date: String
    ) {
        if (numArticle.isEmpty() || msgClient.isEmpty()){
            _showMsg.postValue("Debes llenar todos los campos.")
        }
        else{
            viewModelScope.launch {
                val notification = NotificationModel(
                    article = article,
                    numArticle = numArticle,
                    total = total,
                    date = date
                )
                val chatModel = Chat(
                    msg = msgClient,
                    date = date,
                    type = "out"
                )
                val myUser = User()
                val userData = userRepository.myUser(myUser)
                userData.let { resourceRemoteUser ->
                    when(resourceRemoteUser){
                        is ResourceRemote.Success ->{
                            notification.uname = myUser.name
                            notification.uphone = myUser.phone
                        }
                        is ResourceRemote.Error -> {
                            notification.uname = ""
                            notification.uphone = ""
                        }
                        is ResourceRemote.Loading -> TODO()
                    }
                }
                val result = notificationRepository.crateNotification(notification, chatModel)
                result.let{resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success ->{
                            _createNotification.postValue(result.data)
                            _showMsg.postValue("Se notificó al vendedor de su solicitud ")
                        }
                        is ResourceRemote.Error -> {
                            _showMsg.postValue(result.message)
                        }
                        else ->{
                            //Don´t use
                        }
                    }
                }
            }

        }

    }

}