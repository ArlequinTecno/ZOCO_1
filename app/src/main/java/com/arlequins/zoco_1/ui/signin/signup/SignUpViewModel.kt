package com.arlequins.zoco_1.ui.signin.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.UserRepository
import com.arlequins.zoco_1.model.User
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private lateinit var user: User

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    var registerSuccess: LiveData<String?> = _registerSuccess

    fun validateFields(name: String,
                       email: String,
                       phone: String,
                       passwd: String,
                       repasswd: String) {
        if (name.isEmpty() ||
            email.isEmpty() ||
            phone.isEmpty() ||
            passwd.isEmpty() ||
            repasswd.isEmpty()
        ){
            _errorMsg.value = "Todos los campos son obligatorios"
        }
        else if (email.length<=12 ||
            "@" !in email ||
            email.subSequence(email.length-12, email.length) != "@udea.edu.co"){
            _errorMsg.value = "El correo digitado esta mal escrito o no es un correo institucional de la UdeA"
        }
        else if (phone.length != 10 ){
            _errorMsg.value = "El número de telefono no es válido"
        }
        else if (passwd != repasswd || passwd.length <6){
            _errorMsg.value = "Las contraseñas deben ser iguales y tener mínimo 6 caracteres"
        }
        else{
            viewModelScope.launch {
                val result = userRepository.registerUser(email, passwd)
                result.let{ resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            user = User(result.data, name, email, phone)
                            createUser(user)
                        }
                        is ResourceRemote.Error ->{
                            var msg = result.message
                            when(msg){
                                "The email address is already in use by another account." -> msg = "La cuenta de correo electrónico ya está en uso."
                                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revisa tu conexión a internet."
                            }
                            _errorMsg.postValue(msg)
                        }
                        else -> {
                            //Don´t use
                        }

                    }
                }
            }
        }
    }

    private fun createUser(user: User) {
        viewModelScope.launch{
            val result = userRepository.createUser(user)
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _registerSuccess.postValue(result.data)
                        _errorMsg.postValue("Registro Exitoso")
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else -> {
                        //Don´t use
                    }
                }
            }
        }
    }
}

