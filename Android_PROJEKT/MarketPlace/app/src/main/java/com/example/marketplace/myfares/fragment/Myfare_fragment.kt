package com.example.marketplace.myfares.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.MyApplication
import com.example.marketplace.R
import com.example.marketplace.myfares.adapter.myfare_Adapter
import com.example.marketplace.myfares.model.MyFareInfo
import com.example.marketplace.myfares.viewmodel.MyFare_ViewModel
import com.example.marketplace.myfares.viewmodel.MyFare_ViewModelFactory
import com.example.marketplace.mymarket.adapter.mymarket_Adapter
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory

class Myfare_fragment : Fragment() , myfare_Adapter.OnItemClickListener, myfare_Adapter.OnItemLongClickListener {
    lateinit var myfareViewModel: MyFare_ViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: myfare_Adapter
    private var myProducts: ArrayList<MyFareInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MyFare_ViewModelFactory(Repository())
        myfareViewModel = ViewModelProvider(requireActivity(), factory).get(MyFare_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_myfare_fragment, container, false)
        recycler_view = view.findViewById(R.id.myfare_recycler_view)
        setupRecyclerView()
        myfareViewModel.myFareOrder()
        myfareViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(myfareViewModel.products.value as ArrayList<MyFareInfo>)
            adapter.notifyDataSetChanged()
        }
        //myfareViewModel.products.observe(viewLifecycleOwner){
        //    adapter.setData(myfareViewModel.products.value as ArrayList<MyFareInfo>)
        //    adapter.notifyDataSetChanged()
           // myProducts = myfareViewModel.products.value!!.filter{
             //   !it.owner_username.equals(MyApplication.username)
           // } as ArrayList<MyFareInfo>
         //   adapter.setData(myProducts)
          //  adapter.notifyDataSetChanged()
        //}

        return view
    }

    private fun setupRecyclerView(){
        adapter = myfare_Adapter(ArrayList<MyFareInfo>(), this, this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        //myfareViewModel.currentpos = myfareViewModel.products.value!!.indexOf(myProducts[position])
        findNavController().navigate(R.id.action_mymarket_fragment_to_productdata_fragment)
        Log.d("Adapter", "AdapterPosition: $position")
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }

}