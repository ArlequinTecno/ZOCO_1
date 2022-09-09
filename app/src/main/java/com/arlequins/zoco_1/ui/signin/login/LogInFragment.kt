package com.arlequins.zoco_1.ui.login

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
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        val logInViewModel = ViewModelProvider(this)[LogInViewModel::class.java]

        _binding = FragmentLogInBinding.inflate(inflater, container, false)

        logInViewModel.errorMsg.observe(viewLifecycleOwner){ msg ->
            showErrorMessage(msg)
        }
        logInViewModel.logInSuccess.observe(viewLifecycleOwner){
            goToIndex()
        }
        with(binding){
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


        return binding.root
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