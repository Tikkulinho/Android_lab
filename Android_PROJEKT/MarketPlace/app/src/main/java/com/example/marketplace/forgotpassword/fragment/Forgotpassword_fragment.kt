package com.example.marketplace.forgotpassword.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.forgotpassword.viewmodel.ForgotPassword_ViewModel
import com.example.marketplace.forgotpassword.viewmodel.ForgotPassword_ViewModelFactory
import com.example.marketplace.repository.Repository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ForgotPassword_fragment : Fragment() {
    private lateinit var forgotpasswordViewModel: ForgotPassword_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForgotPassword_ViewModelFactory(Repository())
        forgotpasswordViewModel = ViewModelProvider(this, factory).get(ForgotPassword_ViewModel::class.java)
    }

    //Check if email is correct
    private fun isEmail(text:EditText) :Boolean{
        val EMAIL_PATTERN: Pattern = Pattern.compile("^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}\$")
        val pass:String = text.text.toString()
        return pass.isNotEmpty() && EMAIL_PATTERN.matcher(pass).matches()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgotpassword_fragment, container, false)
        val unamefp = view.findViewById<TextInputEditText>(R.id.editText_usernamefp)
        val emailfp = view.findViewById<TextInputEditText>(R.id.editText_emailfp)
        val usernamefpcheck = view.findViewById<TextInputLayout>(R.id.usernamefp_check)
        val emailfpcheck = view.findViewById<TextInputLayout>(R.id.emailfp_check)
        val button: Button = view.findViewById(R.id.button_emailme)
        //Email me button settings
        button.setOnClickListener {
            usernamefpcheck.setError(null)
            emailfpcheck.setError(null)
            //Check username if empty
            if(unamefp.text.toString().isEmpty()){
                usernamefpcheck.setError("Please enter username.")
            }
            //Check email
            else if(!isEmail(emailfp)){
                emailfpcheck.setError("Email format not correct!")
            }
            //If ok email me
            else {
                usernamefpcheck.setError(null)
                emailfpcheck.setError(null)
                forgotpasswordViewModel.user.value.let {
                    if (it != null) {
                        it.username = unamefp.text.toString()
                    }
                    if (it != null) {
                        it.email = emailfp.text.toString()
                    }
                }
                lifecycleScope.launch {
                    forgotpasswordViewModel.forgotpassword()
                }
                //Send email notification, after go to login page
                val toast = Toast.makeText(activity?.applicationContext, "Email sent.", Toast.LENGTH_SHORT)
                toast.show()
                findNavController().navigate(R.id.action_forgotpassword_fragment_to_login_fragment)
            }
        }
        return view
    }
}