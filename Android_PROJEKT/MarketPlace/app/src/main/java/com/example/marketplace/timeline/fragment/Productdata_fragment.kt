package com.example.marketplace.timeline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory

class Productdata_fragment : Fragment() {

    private lateinit var viewModel: Timeline_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Timeline_ViewModelFactory(Repository())
        viewModel = ViewModelProvider(requireActivity(), factory).get(Timeline_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productdata_fragment, container, false)

        val productItem: Model_Timeline = viewModel.getProduct()

        val seller: TextView = view.findViewById(R.id.textView_username)
        val productName: TextView = view.findViewById(R.id.textView_productname)
        val price: TextView = view.findViewById(R.id.textView_price)
        val active: TextView = view.findViewById(R.id.textView_active)
        val description: TextView = view.findViewById(R.id.textView_description)
        val unit: TextView = view.findViewById(R.id.textView_instock)

        seller.text = productItem.username
        productName.text = productItem.title
        price.text = productItem.price_per_unit
        if(productItem.is_active){
            active.text = "Active"
        }
        else{
            active.text = "Inactive"
        }
        description.text = productItem.description
        unit.text = productItem.units
        return view
    }
}