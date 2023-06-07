package com.vanka.wellnesswave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanka.wellnesswave.databinding.ActivityMainBinding
import com.vanka.wellnesswave.repetedCode.RepetedCode
import com.vanka.wellnesswave.ui.aiChat.AIChatFragment
import com.vanka.wellnesswave.ui.communities.CommunityFragment
import com.vanka.wellnesswave.ui.expert.ExpertFragment
import com.vanka.wellnesswave.ui.home.HomeFrament

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RepetedCode().intentFragment(R.id.frame,AIChatFragment(),this)
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home->{
                 RepetedCode().intentFragment(R.id.frame,HomeFrament(),this)
                    true
                }
                R.id.Chats->{
                    RepetedCode().intentFragment(R.id.frame,AIChatFragment(),this)
                    true
                }
                R.id.Experts->{
                    RepetedCode().intentFragment(R.id.frame,ExpertFragment(),this)
                    true
                }
                R.id.Community->{
                    RepetedCode().intentFragment(R.id.frame,CommunityFragment(),this)
                    true
                }else->{
                RepetedCode().intentFragment(R.id.frame,HomeFrament(),this)
                true
                }
            }
            }

    }
}