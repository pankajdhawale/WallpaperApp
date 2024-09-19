package com.example.wallpaperuserapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperuserapp.Adapter.BomAdapter
import com.example.wallpaperuserapp.Adapter.CatAdapter
import com.example.wallpaperuserapp.Adapter.TheColorToneAdapter
import com.example.wallpaperuserapp.databinding.FragmentHomeBinding
import com.example.wallpaperuserapp.model.bomModel
import com.example.wallpaperuserapp.model.catModel
import com.example.wallpaperuserapp.model.colortoneModel
import com.google.firebase.firestore.FirebaseFirestore

class home : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var db: FirebaseFirestore
    lateinit var bestofmonth: ArrayList<bomModel>
    lateinit var listcolortone: ArrayList<colortoneModel>
    lateinit var listofcategories:ArrayList<catModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // Initialize Firestore and ArrayLists
        db = FirebaseFirestore.getInstance()
        bestofmonth = arrayListOf()
        listcolortone = arrayListOf()
        listofcategories= arrayListOf()

        // Set up RecyclerViews with independent LayoutManagers
        setupRecyclerViews()

        // Fetch data from Firestore for "bestofmonth"
        fetchBestOfMonthData()

        // Fetch data from Firestore for "thecolortone"
        fetchColorToneData()

        // Fetch data from Firestore for "categories"
        fetchCategoryData()

        return binding.root
    }

    private fun setupRecyclerViews() {
        // Set up the RecyclerView for Best of Month
        binding.rcvHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rcvHome.adapter = BomAdapter(requireContext(), bestofmonth)

        // Set up the RecyclerView for Color Tone
        binding.rcvColorTone.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rcvColorTone.adapter = TheColorToneAdapter(requireContext(), listcolortone)

        // Set up the RecyclerView for Categories
        binding.rcvCat.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rcvCat.adapter = CatAdapter(requireContext(),listofcategories)
    }

    private fun fetchBestOfMonthData() {
        db.collection("bestofmonth").addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("@@@@", "Firestore listen failed", error)
                return@addSnapshotListener
            }
            val data = value?.toObjects(bomModel::class.java)
            if (data != null) {
                bestofmonth.clear()
                bestofmonth.addAll(data)
                binding.rcvHome.adapter?.notifyDataSetChanged()

                // Log the data for debugging
                for (i in bestofmonth) {
                    Log.e("@@@@", "OnCreateView: $i")
                }
            }
        }
    }

    private fun fetchColorToneData() {
        db.collection("thecolortone").addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("@@@@", "Firestore listen failed", error)
                return@addSnapshotListener
            }
            val data = value?.toObjects(colortoneModel::class.java)
            if (data != null) {
                listcolortone.clear()
                listcolortone.addAll(data)
                binding.rcvColorTone.adapter?.notifyDataSetChanged()

                // Log the data for debugging
                for (i in listcolortone) {
                    Log.e("bbbb", "OnCreateView: $i")
                }
            }
        }
    }

    private fun fetchCategoryData() {
        db.collection("categories").addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("@@@@", "Firestore listen failed", error)
                return@addSnapshotListener
            }
            val data = value?.toObjects(catModel::class.java)
            if (data != null) {
                listofcategories.clear()
                listofcategories.addAll(data)
                binding.rcvCat.adapter?.notifyDataSetChanged()

                // Log the data for debugging
                for (i in listofcategories) {
                    Log.e("bbbb", "OnCreateView: $i")
                }
            }
        }
    }

}
