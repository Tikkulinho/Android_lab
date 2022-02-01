package com.example.marketplace.register.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.register.viewmodel.Register_ViewModel
import com.example.marketplace.register.viewmodel.Register_ViewModelFactory
import com.example.marketplace.repository.Repository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class Register_fragment : Fragment() {
    private lateinit var registerViewModel: Register_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Register_ViewModelFactory(Repository())
        registerViewModel = ViewModelProvider(this, factory).get(Register_ViewModel::class.java)
    }

    //Check if password is correct
    private fun isPassword(text:EditText) :Boolean{
        val PASSWORD_PATTERN: Pattern = Pattern.compile("[a-zA-Z0-9]{4,24}")
        val pass:String = text.text.toString()
        return pass.isNotEmpty() && PASSWORD_PATTERN.matcher(pass).matches()
    }

    //Check if username is correct
    private fun isUsername(text:EditText) :Boolean{
        val USERNAME_PATTERN: Pattern = Pattern.compile("[a-zA-Z0-9]{4,24}")
        val pass:String = text.text.toString()
        return pass.isNotEmpty() && USERNAME_PATTERN.matcher(pass).matches()
    }

    //Check if email is correct
    private fun isEmail(text:EditText) :Boolean{
        val EMAIL_PATTERN: Pattern = Pattern.compile("^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}\$")
        val pass:String = text.text.toString()
        return pass.isNotEmpty() && EMAIL_PATTERN.matcher(pass).matches()
    }

    //Phone number has to be a romanian valid phone number
    private fun isPhone(text:EditText) :Boolean{
        val PHONE_PATTERN: Pattern = Pattern.compile("^(?:(?:(?:00\\s?|\\+)40\\s?|0)(?:7\\d{2}\\s?\\d{3}\\s?\\d{3}|(21|31)\\d{1}\\s?\\d{3}\\s?\\d{3}|((2|3)[3-7]\\d{1})\\s?\\d{3}\\s?\\d{3}|(8|9)0\\d{1}\\s?\\d{3}\\s?\\d{3}))\$")
        val pass:String = text.text.toString()
        return pass.isNotEmpty() && PHONE_PATTERN.matcher(pass).matches()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_fragment, container, false)
        val unamer = view.findViewById<TextInputEditText>(R.id.editText_usernameregister)
        val emailr = view.findViewById<TextInputEditText>(R.id.editText_emailregister)
        val pwordr = view.findViewById<TextInputEditText>(R.id.editText_passwordregister)
        val phoner = view.findViewById<TextInputEditText>(R.id.editText_phoneregister)
        val usernamercheck = view.findViewById<TextInputLayout>(R.id.usernameregistration_check)
        val emailrcheck = view.findViewById<TextInputLayout>(R.id.emailregistration_check)
        val passwordrcheck = view.findViewById<TextInputLayout>(R.id.passwordregistration_check)
        val phonercheck = view.findViewById<TextInputLayout>(R.id.phoneregistration_check)
        val button: Button = view.findViewById(R.id.button_register)
        button.setOnClickListener {
            usernamercheck.setError(null)
            emailrcheck.setError(null)
            passwordrcheck.setError(null)
            phonercheck.setError(null)
            //Check username
            if(!isUsername(unamer))
            {
                usernamercheck.setError("Username length [4-24]!")
            }
            //Check email
            else if(!isEmail(emailr)){
                emailrcheck.setError("Email format not correct!")
            }
            //Check password
            else if(!isPassword(pwordr)){
                passwordrcheck.setError("Password length [4-24]!")
            }
            //Check phone
            else if(!isPhone(phoner)){
                phonercheck.setError("Please input romanian number!")
            }
            //If ok then registration settings
            else{
                usernamercheck.setError(null)
                emailrcheck.setError(null)
                passwordrcheck.setError(null)
                phonercheck.setError(null)
                registerViewModel.user.value.let {
                    if (it != null) {
                        it.username = unamer.text.toString()
                    }
                    if (it != null) {
                        it.email = emailr.text.toString()
                    }
                    if (it != null) {
                        it.password = pwordr.text.toString()
                    }
                    if (it != null) {
                        it.phone_number = phoner.text.toString()
                    }
                }
                lifecycleScope.launch {
                    registerViewModel.register()
                }
                //After registration go to login screen
                findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
            }
        }
        //If already have an account go to login screen
        val button2: Button = view.findViewById(R.id.button_loginregister)
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
        }

        return view
    }
}