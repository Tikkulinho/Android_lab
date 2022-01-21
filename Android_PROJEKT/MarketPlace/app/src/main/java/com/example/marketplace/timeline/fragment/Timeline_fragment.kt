package com.example.marketplace.timeline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.repository.Repository
import com.example.marketplace.timeline.adapter.Timeline_Adapter
import com.example.marketplace.timeline.model.Model_Timeline
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModel
import com.example.marketplace.timeline.viewmodel.Timeline_ViewModelFactory

class Timeline_fragment : Fragment() , Timeline_Adapter.OnItemClickListener, Timeline_Adapter.OnItemLongClickListener {
    lateinit var listViewModel: Timeline_ViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: Timeline_Adapter

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
        val view =  inflater.inflate(R.layout.fragment_timeline_fragment, container, false)
        recycler_view = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.products.value as ArrayList<Model_Timeline>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    private fun setupRecyclerView(){
        adapter = Timeline_Adapter(ArrayList<Model_Timeline>(), this, this)
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