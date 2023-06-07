package com.vanka.wellnesswave.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vanka.wellnesswave.MainActivity
import com.vanka.wellnesswave.databinding.ChatItemLayoutBinding
import com.vanka.wellnesswave.databinding.FragmentAIChatBinding
import com.vanka.wellnesswave.model.ChatModel
import com.vanka.wellnesswave.repetedCode.RepetedCode
import okhttp3.*
import java.io.IOException

class AiChatAdapter(val context:Context,val list:ArrayList<ChatModel>):RecyclerView.Adapter<AiChatAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ChatItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ChatItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         holder.binding.textS.text = list[position].Sendmessage
        holder.binding.textR.text = list[position].reciveMessage

      //   callOpenAIAPI(holder.binding.textR,"I want you to act as my friend. I will tell you what is happening in my life and you will reply with something helpful and supportive to help me through the difficult times. Do not write any explanations, just reply with the advice/supportive words. My first request is ${list[position]}")





    }
    fun callOpenAIAPI(textView: TextView, prompt: String) {
        val aApiKey = "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"
        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
            .add("prompt", prompt)
            .build()

        val request = Request.Builder()
            .url("https://api.openai.com/v1/engines/davinci-codex/completions")
            .addHeader("Authorization", "Bearer $aApiKey")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle request failure
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
               textView.text = responseBody
            }
        })
    }
}