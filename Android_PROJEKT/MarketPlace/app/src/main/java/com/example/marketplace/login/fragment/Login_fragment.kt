package com.example.marketplace.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.login.viewmodel.Login_ViewModel
import com.example.marketplace.login.viewmodel.Login_ViewModelFactory
import com.example.marketplace.repository.Repository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
        val view = inflater.inflate(R.layout.fragment_login_fragment, container, false)
        val uname = view.findViewById<TextInputEditText>(R.id.editText_usernamelogin)
        val pword = view.findViewById<TextInputEditText>(R.id.editText_passwordlogin)
        val passwordcheck = view.findViewById<TextInputLayout>(R.id.password_check)
        val usernamecheck = view.findViewById<TextInputLayout>(R.id.username_check)
        val button: Button = view.findViewById(R.id.button_login)
        //Login button settings
        button.setOnClickListener {
            passwordcheck.setError(null)
            usernamecheck.setError(null)
            //Check if username box is empty
            if(uname.text.toString().isEmpty()) {
                usernamecheck.setError("Please enter username.")
            }
            //Check if password box is empty
            else if(pword.text.toString().isEmpty()){
                passwordcheck.setError("Please enter password.")
            }
            //If not empty then login
            else{
                passwordcheck.setError(null)
                usernamecheck.setError(null)
                loginViewModel.user.value.let {
                    if (it != null) {
                        it.username = uname.text.toString()
                    }
                    if (it != null) {
                        it.password = pword.text.toString()
                    }
                }
                lifecycleScope.launch {
                    loginViewModel.login()
                }
                //After login enter timeline screen
                loginViewModel.token.observe(viewLifecycleOwner){
                    findNavController().navigate(R.id.action_login_fragment_to_timeline_fragment)
            }
        }
        }
        //Click here link to forgot password screen
        val textView: TextView = view.findViewById(R.id.textView_clickhere)
        textView.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_forgotpassword_fragment)
        }
        //Sign up button to registration screen
        val button2: Button = view.findViewById(R.id.button_signup)
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_login_fragment_to_register_fragment)
        }
        return view
    }
}