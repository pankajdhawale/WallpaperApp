package com.example.wallpaperuserapp

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperuserapp.Adapter.CatImagesAdapter
import com.example.wallpaperuserapp.databinding.ActivityCategoryBinding
import com.example.wallpaperuserapp.model.bomModel
import com.example.wallpaperuserapp.model.catModel
import com.google.firebase.firestore.FirebaseFirestore

class CategoryActivity : AppCompatActivity() {

    lateinit var binding:ActivityCategoryBinding
    lateinit var db: FirebaseFirestore
    lateinit var listofCategoryWallpaper:ArrayList<bomModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)

        // Initialize Firestore and ArrayLists
        db = FirebaseFirestore.getInstance()
        //get id of current document from categories
        val uid=intent.getStringExtra("uid")
        listofCategoryWallpaper = arrayListOf()
        // Set up the RecyclerView for Categories
        binding.catRecy.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.catRecy.adapter = CatImagesAdapter(this,listofCategoryWallpaper)

        db.collection("categories").document(uid!!).collection("wallpaper").addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("@@@@", "Firestore listen failed", error)
                return@addSnapshotListener
            }
            val data = value?.toObjects(bomModel::class.java)
            if (data != null) {
                listofCategoryWallpaper.clear()
                listofCategoryWallpaper.addAll(data)
                binding.catRecy.adapter?.notifyDataSetChanged()

                // Log the data for debugging
                for (i in listofCategoryWallpaper) {
                    Log.e("cccc", "OnCreateView: $i")
                }
            }
    }
}
}