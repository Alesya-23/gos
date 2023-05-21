package com.example.examgos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.examgos.databinding.FragmentListBinding


class ListFragment :  Fragment(R.layout.fragment_list){
    private lateinit var binding: FragmentListBinding
    private lateinit var listFilms: List<Film>
    // TODO: Rename and change types of parameters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        addButtonClick()
        getListObject()
        reportClick()
    }

    override fun onResume() {
        super.onResume()

    }

    init {
        listFilms = emptyList()
    }

    fun addButtonClick(){
        binding.addFilmBtn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, AddFragment())
                .addToBackStack("AddElement")
                .commit()
        }
    }

    fun reportClick(){
        binding.report.setOnClickListener {
            val intent = Intent(context, ReportActivity::class.java)
            context?.startActivity(intent)
        }
    }

    fun getListObject(){
        listFilms = (activity as MainActivity).database?.filmDao()
            ?.getAll()!!
        if(listFilms.isEmpty()){
          listFilms =  emptyList<Film>()
        } else
        {
            val recyclerView = binding.list
            recyclerView.adapter = CustomAdapter(listFilms)
        }
    }
}