package com.arlequins.zoco_1.ui.signin.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {
    private lateinit var logInBinding: FragmentLogInBinding
    private lateinit var logInViewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        logInViewModel = ViewModelProvider(this)[LogInViewModel::class.java]

        logInBinding = FragmentLogInBinding.inflate(inflater, container, false)

        logInViewModel.errorMsg.observe(viewLifecycleOwner){ msg ->
            showErrorMessage(msg)
        }
        logInViewModel.logInSuccess.observe(viewLifecycleOwner){
            goToIndex()
        }
        with(logInBinding){
            loginButton.setOnClickListener {
                logInViewModel.validateFields(
                    loginEmailInputText.text.toString(),
                    loginPasswdInputText.text.toString()
                )
            }
            loginToSignupTextView.setOnClickListener{
               goToSignUp()
            }
        }


        return logInBinding.root
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    private fun goToIndex(){
        findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToNavIndex())
    }
    private fun goToSignUp(){
        findNavController().navigate(LogInFragmentDirections.actionNavLogInToSignUpFragment())
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}