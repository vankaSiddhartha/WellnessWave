package com.vanka.wellnesswave.ui.aiChat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vanka.wellnesswave.databinding.FragmentAIChatBinding




class AIChatFragment : Fragment() {
//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
   private lateinit var binding:FragmentAIChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAIChatBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }


}