package com.example.marketplace.forgotpassword.fragment

import android.os.Bundle
import android.util.Log
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
import com.example.marketplace.register.viewmodel.Register_ViewModel
import com.example.marketplace.register.viewmodel.Register_ViewModelFactory
import com.example.marketplace.repository.Repository
import kotlinx.coroutines.launch

class ForgotPassword_fragment : Fragment() {
    private lateinit var forgotpasswordViewModel: ForgotPassword_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Register_ViewModelFactory(Repository())
        forgotpasswordViewModel = ViewModelProvider(this, factory).get(ForgotPassword_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgotpassword_fragment, container, false)
        val editText1: EditText = view.findViewById(R.id.editTextTextPersonName2)
        val editText2: EditText = view.findViewById(R.id.editTextTextEmailAddress3)
        val button: Button = view.findViewById(R.id.button_emailme)
        button.setOnClickListener {
            forgotpasswordViewModel.user.value.let {
                if (it != null) {
                    it.username = editText1.text.toString()
                }
                if (it != null) {
                    it.email = editText2.text.toString()
                }
            }
            lifecycleScope.launch {
                forgotpasswordViewModel.forgotpassword()
            }
            Toast.makeText(activity?.applicationContext,"Send an email.", Toast.LENGTH_LONG)

        }

        return view
    }
}