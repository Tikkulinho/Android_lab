package com.example.marketplace.timeline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketplace.MyApplication
import com.example.marketplace.R
import com.example.marketplace.myfares.viewmodel.MyFare_ViewModel
import com.example.marketplace.myfares.viewmodel.MyFare_ViewModelFactory
import com.example.marketplace.productupdate.viewmodel.ProductUpdate_ViewModel
import com.example.marketplace.productupdate.viewmodel.ProductUpdate_ViewModelFactory
import com.example.marketplace.profile.viewmodel.Profile_ViewModel
import com.example.marketplace.profile.viewmodel.Profile_ViewModelFactory
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory

class Productdata_fragment : Fragment() {

    private lateinit var viewModel: Timeline_ViewModel
    private lateinit var profileViewModel: Profile_ViewModel
    private lateinit var updateproductViewModel: ProductUpdate_ViewModel
    private lateinit var myfareViewModel: MyFare_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Timeline_ViewModelFactory(Repository())
        val factoryprofile = Profile_ViewModelFactory(Repository())
        val factoryproductupdate = ProductUpdate_ViewModelFactory(Repository())
        val factorymyfare = MyFare_ViewModelFactory(Repository())
        viewModel = ViewModelProvider(requireActivity(), factory).get(Timeline_ViewModel::class.java)
        profileViewModel = ViewModelProvider(requireActivity(),factoryprofile).get(Profile_ViewModel::class.java)
        updateproductViewModel = ViewModelProvider(requireActivity(), factoryproductupdate).get(ProductUpdate_ViewModel::class.java)
        myfareViewModel = ViewModelProvider(requireActivity(), factorymyfare).get(MyFare_ViewModel::class.java)
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
        productName.text = productItem.title.drop(1).dropLast(1)
        price.text = productItem.price_per_unit.drop(1).dropLast(1)
        if(productItem.is_active){
            active.text = "Active"
        }
        else{
            active.text = "Inactive"
        }
        description.text = productItem.description.drop(1).dropLast(1)
        unit.text = productItem.units.drop(1).dropLast(1)

        seller.setOnClickListener {
            profileViewModel.user.value!!.username = productItem.username
            findNavController().navigate(R.id.action_productdata_fragment_to_profileOthers_fragment)
        }

        val button = view.findViewById<Button>(R.id.button_updateproductinfo)
        val button2 = view.findViewById<Button>(R.id.button_removeproduct)
        val button3 = view.findViewById<Button>(R.id.button_order)
        if(seller.text != MyApplication.username){
            button.visibility = GONE
            button2.visibility = GONE
        }
        else{
            button3.visibility = GONE
        }
        button.setOnClickListener(){
            updateproductViewModel.product.value!!.product_id = productItem.product_id
            findNavController().navigate(R.id.action_productdata_fragment_to_productUpdate_fragment)
        }
        button2.setOnClickListener(){
            viewModel.productRemove()
            viewModel.getProducts()
            findNavController().navigate(R.id.action_productdata_fragment_to_mymarket_fragment)
        }
        viewModel.order.value.let{
            if (it != null) {
                it.title = productName.text.toString()
            }
            if (it != null) {
                it.description = description.text.toString()
            }
            if (it != null) {
                it.price_per_unit = price.text.toString()
            }
            if (it != null) {
                it.units = unit.text.toString()
            }
            if (it != null) {
                it.is_active = productItem.is_active
            }
            if (it != null) {
                it.owner_username = seller.text.toString()
            }
        }
        button3.setOnClickListener(){
            viewModel.addOrder()
            myfareViewModel.myFareOrder()
            findNavController().navigate(R.id.action_productdata_fragment_to_myfare_fragment)
        }

        return view
    }
}