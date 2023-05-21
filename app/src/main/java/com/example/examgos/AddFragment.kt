package com.example.examgos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.examgos.databinding.FragmentAddBinding


class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var addFragmentBinding: FragmentAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFragmentBinding = FragmentAddBinding.bind(view)
        addButtonClick()

    }

    private fun addButtonClick(){
        addFragmentBinding.addFilmDetailsBtn.setOnClickListener {
            if(addFragmentBinding.name.text.isNotEmpty() && addFragmentBinding.year.text.isNotEmpty()){
                (activity as MainActivity).database?.filmDao()
                    ?.insertAll(Film(0, addFragmentBinding.name.text.toString(), addFragmentBinding.year.text.toString()))
            }
        }
    }

    companion object {
        fun newInstance(){}
    }
}