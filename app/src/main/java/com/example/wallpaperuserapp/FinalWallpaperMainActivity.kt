package com.example.wallpaperuserapp

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.wallpaperuserapp.databinding.ActivityFinalWallpaperMainBinding
import kotlinx.coroutines.*
import java.net.URL

class FinalWallpaperMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinalWallpaperMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFinalWallpaperMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val url = intent.getStringExtra("Link")

        if (url != null) {
            Glide.with(this).load(url).into(binding.finalwallpaper)
        }

        binding.btndownload.setOnClickListener {
            if (url != null) {
                downloadImage(url)
            }
        }

        binding.btnsetwallpaper.setOnClickListener {
            if (url != null) {
                setWallpaper(url)
            }
        }
    }

    private fun downloadImage(url: String) {
        // Use coroutines to download the image in the background
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bitmap = withContext(Dispatchers.IO) { Glide.with(this@FinalWallpaperMainActivity).asBitmap().load(url).submit().get() }
                // Save the bitmap to storage (implementation depends on your storage requirements)
                saveBitmapToStorage(bitmap)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FinalWallpaperMainActivity, "Image downloaded successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FinalWallpaperMainActivity, "Failed to download image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setWallpaper(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                val bitmap = withContext(Dispatchers.IO) { Glide.with(this@FinalWallpaperMainActivity).asBitmap().load(url).submit().get() }
                wallpaperManager.setBitmap(bitmap)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FinalWallpaperMainActivity, "Wallpaper set successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FinalWallpaperMainActivity, "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveBitmapToStorage(bitmap: Bitmap) {
        // Implement logic to save the bitmap to the device storage
    }
}
