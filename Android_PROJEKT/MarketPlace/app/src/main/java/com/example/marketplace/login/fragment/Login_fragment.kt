package com.example.marketplace.login.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.login.viewmodel.Login_ViewModel
import com.example.marketplace.login.viewmodel.Login_ViewModelFactory
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class Login_fragment : Fragment() {
    private lateinit var loginViewModel: Login_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Login_ViewModelFactory(Repository())
        loginViewModel = ViewModelProvider(this, factory).get(Login_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_fragment, container, false)
        val editText1: EditText = view.findViewById(R.id.editTextTextPersonName)
        val editText2: EditText = view.findViewById(R.id.editTextTextPassword)
        val button: Button = view.findViewById(R.id.button_login)
        button.setOnClickListener {
            loginViewModel.user.value.let {
                if (it != null) {
                    it.username = editText1.text.toString()
                }
                if (it != null) {
                    it.password = editText2.text.toString()
                }
            }
            lifecycleScope.launch {
                loginViewModel.login()
            }

        }
        loginViewModel.token.observe(viewLifecycleOwner){
            Log.d("xxx", "navigate to list")
            findNavController().navigate(R.id.action_login_fragment_to_timeline_fragment)
        }
        val textView: TextView = view.findViewById(R.id.textView3_clickhere)
        textView.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_forgotpassword_fragment)
        }
        val button2: Button = view.findViewById(R.id.button2_signup)
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_login_fragment_to_register_fragment)
        }
        return view
    }
}