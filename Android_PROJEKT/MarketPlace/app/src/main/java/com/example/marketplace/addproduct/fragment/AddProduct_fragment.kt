package com.example.marketplace.addproduct.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.R
import com.example.marketplace.addproduct.viewmodel.AddProduct_ViewModel
import com.example.marketplace.addproduct.viewmodel.AddProduct_ViewModelFactory
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory
import androidx.navigation.fragment.findNavController

class AddProduct_fragment : Fragment() {
    private lateinit var addViewModel: AddProduct_ViewModel
    private lateinit var listViewModel: Timeline_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddProduct_ViewModelFactory(Repository())
        val factory2 = Timeline_ViewModelFactory(Repository())
        addViewModel = ViewModelProvider(this, factory).get(AddProduct_ViewModel::class.java)
        listViewModel = ViewModelProvider(this, factory2).get(Timeline_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_product_fragment, container, false)
        val title = view.findViewById<EditText>(R.id.TextView_add_name)
        val price = view.findViewById<EditText>(R.id.TextView_add_price)
        val amount = view.findViewById<EditText>(R.id.TextView_add_amount)
        val description = view.findViewById<EditText>(R.id.TextView_add_description)
        val isActive = view.findViewById<Switch>(R.id.Switch_add_activeorinactive)

        val button = view.findViewById<Button>(R.id.Button_add_addproduct)

        button.setOnClickListener {
            addViewModel.product.value.let {
                if (it != null) {
                    it.title = title.text.toString()
                }
                if (it != null) {
                    it.price_per_unit = price.text.toString()
                }
                if(it!=null){
                    it.units = amount.text.toString()
                }
                if(it!=null){
                    it.description = description.text.toString()
                }
                if(it!=null){
                    it.is_active = isActive.isChecked
                }
            }
            addViewModel.addProduct()
            listViewModel.getProducts()
            findNavController().navigate(R.id.action_mymarket_fragment_to_addProduct_fragment)
        }


        return view
    }
}