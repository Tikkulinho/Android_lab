package com.example.marketplace.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.MyApplication
import com.example.marketplace.R
import com.example.marketplace.profile.viewmodel.Profile_ViewModel
import com.example.marketplace.profile.viewmodel.Profile_ViewModelFactory
import com.example.marketplace.repository.Repository

class Profile_fragment : Fragment() {

    private lateinit var viewModel: Profile_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Profile_ViewModelFactory(Repository())
        viewModel = ViewModelProvider(requireActivity(),factory).get(Profile_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =  inflater.inflate(R.layout.fragment_profile_fragment, container, false)

        val name = view.findViewById<TextView>(R.id.textView_name)
        val email = view.findViewById<TextView>(R.id.textView_actualemail)
        val username = view.findViewById<TextView>(R.id.textView_actualprofileusername)
        val phone = view.findViewById<TextView>(R.id.textView_actualprofilephone)
        val button = view.findViewById<Button>(R.id.button_update)

        viewModel.user.value!!.username = MyApplication.username
        viewModel.getUserInfo()
        viewModel.user.observe(viewLifecycleOwner){
            email.text = it.email
            name.text = it.username
            username.text = it.username
            phone.text = it.phone_number
        }

        button.setOnClickListener {
            findNavController().navigate(R.id.action_profile_fragment_to_update_fragment)
        }


        return view
    }


}