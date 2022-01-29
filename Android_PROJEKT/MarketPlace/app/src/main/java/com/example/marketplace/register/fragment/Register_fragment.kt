package com.example.marketplace.register.fragment

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.launch

class Register_fragment : Fragment() {
    private lateinit var registerViewModel: Register_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Register_ViewModelFactory(Repository())
        registerViewModel = ViewModelProvider(this, factory).get(Register_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_fragment, container, false)
        val editText1: EditText = view.findViewById(R.id.editTextTextPersonName_username)
        val editText2: EditText = view.findViewById(R.id.editTextTextEmailAddress2)
        val editText3: EditText = view.findViewById(R.id.editTextTextPassword2)
        val button: Button = view.findViewById(R.id.button_register)
        button.setOnClickListener {
            registerViewModel.user.value.let {
                if (it != null) {
                    it.username = editText1.text.toString()
                }
                if (it != null) {
                    it.email = editText2.text.toString()
                }
                if (it != null) {
                    it.password = editText3.text.toString()
                }
            }
            lifecycleScope.launch {
                registerViewModel.register()
            }
            Log.d("xxx", "navigate to list")
            findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
        }
        val button2: Button = view.findViewById(R.id.button2_login)
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
        }

        return view
    }
}