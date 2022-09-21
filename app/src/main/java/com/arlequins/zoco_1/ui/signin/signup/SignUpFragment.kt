package com.arlequins.zoco_1.ui.signin.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)

        signUpViewModel.errorMsg.observe(viewLifecycleOwner){ msg: String? ->
            showErrorMessage(msg)
        }
        signUpViewModel.registerSuccess.observe(viewLifecycleOwner){
            goToLogin()
        }

        with (signUpBinding){
            signupRegisterButton.setOnClickListener {
                signUpViewModel.validateFields(
                    signupNameEditText.text.toString(),
                    signupEmailEditText.text.toString(),
                    signupPhoneEditText.text.toString(),
                    signupPasswdEditText.text.toString(),
                    signupRepasswdEditText.text.toString()
                )
            }
        }

        return signUpBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
    private fun goToLogin(){
        findNavController().navigate(SignUpFragmentDirections.actionNavSignupToNavLogIn())
    }
}