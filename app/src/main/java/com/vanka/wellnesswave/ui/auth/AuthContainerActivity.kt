package com.vanka.wellnesswave.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanka.wellnesswave.databinding.ActivityAuthContainerBinding

class AuthContainerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAuthContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}