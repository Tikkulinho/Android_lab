package com.example.marketplace.update.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.update.viewmodel.Update_ViewModel
import com.example.marketplace.update.viewmodel.Update_ViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class Update_fragment : Fragment() {
    private lateinit var updateViewModel: Update_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Update_ViewModelFactory(Repository())
        updateViewModel = ViewModelProvider(requireActivity(), factory).get(Update_ViewModel::class.java)
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
        val view: View = inflater.inflate(R.layout.fragment_update_fragment, container, false)

        val unameup = view.findViewById<TextInputEditText>(R.id.editText_usernameupdate)
        val emailup = view.findViewById<TextInputEditText>(R.id.editText_emailupdate)
        val phoneup = view.findViewById<TextInputEditText>(R.id.editText_phoneupdate)
        val usernameupcheck = view.findViewById<TextInputLayout>(R.id.usernameupdate_check)
        val emailupcheck = view.findViewById<TextInputLayout>(R.id.emailupdate_check)
        val phoneupdatecheck = view.findViewById<TextInputLayout>(R.id.phoneupdate_check)
        val button = view.findViewById<Button>(R.id.button_saveupdate)

        button.setOnClickListener {
            usernameupcheck.setError(null)
            emailupcheck.setError(null)
            phoneupdatecheck.setError(null)
            if(!isUsername(unameup))
            {
                usernameupcheck.setError("Username length [4-24]!")
            }
            //Check email
            else if(!isEmail(emailup)){
                emailupcheck.setError("Email format not correct!")
            }
            //Check phone number
            else if(!isPhone(phoneup)){
                phoneupdatecheck.setError("Please input romanian number!")
            }
            //If ok then update settings
            else {
                usernameupcheck.setError(null)
                emailupcheck.setError(null)
                phoneupdatecheck.setError(null)
                updateViewModel.user.value.let {
                    if (it != null) {
                        it.username = unameup.text.toString()
                    }
                    if (it != null) {
                        it.email = emailup.text.toString()
                    }
                    if (it != null) {
                        it.phone_number = phoneup.text.toString()
                    }
                }
                updateViewModel.updateUser()
                //After update navigate to profile screen
                findNavController().navigate(R.id.action_update_fragment_to_profile_fragment)
            }
        }
        return view
    }

}