package com.example.wallpaperuserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.wallpaperuserapp.databinding.ActivityMainBinding
import com.example.wallpaperuserapp.fragment.download
import com.example.wallpaperuserapp.fragment.home

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)

        //default fragment open
        replaceFragment(home())

        binding.icHome.setOnClickListener {
                replaceFragment(home())
        }
        binding.icDownload.setOnClickListener {
                replaceFragment(download())
        }

    }

    fun replaceFragment(fragment:Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.replacefragment,fragment).commit()
    }

}