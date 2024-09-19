package com.example.wallpaperuserapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpaperuserapp.R
import com.example.wallpaperuserapp.databinding.FragmentDownloadBinding


class download : Fragment() {

    lateinit var binding:FragmentDownloadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentDownloadBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}