package com.example.marketplace.update.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.MyApplication
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.update.viewmodel.Update_ViewModel
import com.example.marketplace.update.viewmodel.Update_ViewModelFactory

class Update_fragment : Fragment() {
    private lateinit var updateViewModel: Update_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Update_ViewModelFactory(Repository())
        updateViewModel = ViewModelProvider(requireActivity(), factory).get(Update_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_update_fragment, container, false)

        val email = view.findViewById<EditText>(R.id.editText_emailupdate)
        val username = view.findViewById<EditText>(R.id.editText_usernameupdate)
        val phone = view.findViewById<EditText>(R.id.editText_phoneupdate)
        val button = view.findViewById<Button>(R.id.button_saveupdate)

        button.setOnClickListener {
            updateViewModel.user.value.let {
                if (it != null) {
                    it.username = username.text.toString()
                }
                if (it != null) {
                    it.email = email.text.toString()
                }
                if (it != null) {
                    it.phone_number = phone.text.toString()
                }
            }
            updateViewModel.updateUser()
            findNavController().navigate(R.id.action_update_fragment_to_profile_fragment)
        }
        return view
    }

}