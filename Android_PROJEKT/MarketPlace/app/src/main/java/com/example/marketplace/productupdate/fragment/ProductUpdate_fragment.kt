package com.example.marketplace.productupdate.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.R
import com.example.marketplace.productupdate.viewmodel.ProductUpdate_ViewModel
import com.example.marketplace.productupdate.viewmodel.ProductUpdate_ViewModelFactory
import com.example.marketplace.repository.Repository

class ProductUpdate_fragment : Fragment() {
    private lateinit var productUpdateViewModel: ProductUpdate_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProductUpdate_ViewModelFactory(Repository())
        productUpdateViewModel = ViewModelProvider(requireActivity(), factory).get(ProductUpdate_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_update_fragment, container, false)

        val title = view.findViewById<EditText>(R.id.editText_upititle)
        val price  = view.findViewById<EditText>(R.id.editText_upiprice)
        val isActive = view.findViewById<Switch>(R.id.switch_upi)
        val units = view.findViewById<EditText>(R.id.editText_upiamount)
        val description = view.findViewById<EditText>(R.id.editText_upidescription)

        val button = view.findViewById<Button>(R.id.button_upiupdate)
        button.setOnClickListener {
            productUpdateViewModel.product.value.let {
                if (it != null) {
                    it.title = title.text.toString()
                }
                if (it != null) {
                    it.price_per_unit = price.text.toString()
                }
                if (it != null) {
                    it.is_active = isActive.isChecked
                }
                if(it!= null){
                    it.units = units.text.toString()
                }
                if(it!=null){
                    it.description = description.text.toString()
                }
            }
            productUpdateViewModel.productUpdate()
            findNavController().navigate(R.id.action_productUpdate_fragment_to_productdata_fragment)
        }
        return view
    }
}