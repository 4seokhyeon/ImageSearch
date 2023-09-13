package com.ImageSearch.searchPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.R
import com.example.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
   private lateinit var binding:FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      binding =  FragmentSearchBinding.inflate(inflater, container,false)
        return binding.root


    }

    companion object{
        fun newInstacne() = SearchFragment()
    }

}