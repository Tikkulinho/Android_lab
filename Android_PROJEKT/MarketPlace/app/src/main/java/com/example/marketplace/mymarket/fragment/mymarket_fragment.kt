package com.example.marketplace.mymarket.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.MyApplication
import com.example.marketplace.R
import com.example.marketplace.mymarket.adapter.mymarket_Adapter
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.model.Model_Timeline
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory

class mymarket_fragment : Fragment() , mymarket_Adapter.OnItemClickListener, mymarket_Adapter.OnItemLongClickListener {
    lateinit var listViewModel: Timeline_ViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: mymarket_Adapter
    private var myProducts: ArrayList<Model_Timeline> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = Timeline_ViewModelFactory(Repository())
        listViewModel = ViewModelProvider(this, factory).get(Timeline_ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_mymarket_fragment, container, false)
        recycler_view = view.findViewById(R.id.mymarket_recycler_view)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.products.value as ArrayList<Model_Timeline>)
            adapter.notifyDataSetChanged()
            myProducts = listViewModel.products.value!!.filter{
                it.username.equals(MyApplication.username)
            } as ArrayList<Model_Timeline>
            adapter.setData(myProducts)
            adapter.notifyDataSetChanged()
        }

        val button = view.findViewById<View>(R.id.addButton)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_mymarket_fragment_to_addProduct_fragment)
        }

        return view
    }

    private fun setupRecyclerView(){
        adapter = mymarket_Adapter(ArrayList<Model_Timeline>(), this, this)
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
//        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }

}