package com.ImageSearch.storgepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.R
import com.example.databinding.FragmentBoxBinding


class BoxFragment : Fragment() {
    lateinit var binding:FragmentBoxBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentBoxBinding.inflate(inflater,container,false)
        return binding.root
    }


}