package com.arlequins.zoco_1.ui.signin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlequins.zoco_1.data.ResourceRemote
import com.arlequins.zoco_1.data.UserRepository

import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    private var userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _logInSuccess: MutableLiveData<String?> = MutableLiveData()
    val logInSuccess: LiveData<String?> = _logInSuccess

    fun validateFields(email: String,
                      passwd: String) {

        if (email.isEmpty() || passwd.isEmpty()){
            _errorMsg.value = "Debes llenar todos los campos"
        }
        else if (email.length<=12 ||
            "@" !in email ||
            email.subSequence(email.length-12, email.length) != "@udea.edu.co"){
            _errorMsg.value = "El correo digitado esta mal escrito o no es un correo institucional de la UdeA"
        }
        else if (passwd.length < 6){
            _errorMsg.value = "Contraseña incorrecta"
        }
        else{
            viewModelScope.launch {
                val result = userRepository.logInUser(email, passwd)
                result.let { resourceRemote ->
                    when (resourceRemote){
                        is ResourceRemote.Success->{
                            _logInSuccess.postValue(result.data)
                            _errorMsg.postValue("Inicio de sesión exitoso")
                        }
                        is ResourceRemote.Error ->{
                            var msg = result.message
                            when(msg){
                                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revisa tu conexión a internet."
                                "The password is invalid or the user does not have a password." -> msg = "Contraseña incorrecta!"
                                "There is no user record corresponding to this identifier. The user may have been deleted." -> msg = "No existe una cuenta asociada a esta dirección de correo"
                            }
                            _errorMsg.postValue(msg)
                        }
                        else ->{
                            //no se usa
                        }

                    }
                }

            }
        }
    }

}