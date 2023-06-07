package com.vanka.wellnesswave.ui.aiChat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.wellnesswave.adapter.AiChatAdapter
import com.vanka.wellnesswave.databinding.FragmentAIChatBinding
import com.vanka.wellnesswave.model.ChatModel
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class AIChatFragment : Fragment() {
//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
   private lateinit var binding:FragmentAIChatBinding
   private lateinit var list:ArrayList<ChatModel>
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentAIChatBinding.inflate(LayoutInflater.from(requireContext()))
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.sendButton.setOnClickListener {
            upDateAdapter(binding.messageEditText.text.toString())
        }
        list = ArrayList<ChatModel>()
        return binding.root
    }

    private fun upDateAdapter(chat: String) {

    //callOpenAIAPI(chat,"I want you to act as my friend. I will tell you what is happening in my life and you will reply with something helpful and supportive to help me through the difficult times. Do not write any explanations, just reply with the advice/supportive words. My first request is ${chat}")
        var question = "I want you to act as my friend. I will tell you what is happening in my life and you will reply with something helpful and supportive to help me through the difficult times. Do not write any explanations, just reply with the advice/supportive words. My first request is ${chat}"
        getResponse(question,){res->
            list.add(ChatModel(chat,res))
            activity?.runOnUiThread {
                binding.chatRecyclerView.adapter = AiChatAdapter(requireContext(), list)
            }
        }


    }
    fun callOpenAIAPI( chat: String,prompt: String) {
        val aApiKey = "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"
        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
            .add("prompt", prompt)
            .build()

        val request = Request.Builder()
            //.url("https://api.openai.com/v1/engines/davinci-codex/completions")
            .url("https://api.openai.com/v1/completions")
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
                Log.e("error",responseBody.toString())

                list.add(ChatModel(chat,responseBody))
                activity?.runOnUiThread {
                    binding.chatRecyclerView.adapter = AiChatAdapter(requireContext(), list)
                }

            }
        })
    }
    fun getResponse(question: String, callback: (String) -> Unit){

        // setting text on for question on below line.
    //    idTVQuestion.text = question
      //  etQuestion.setText("")

        val apiKey="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"
        val url="https://api.openai.com/v1/engines/text-davinci-003/completions"

        val requestBody="""
            {
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error","API failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
              val body=response.body?.string()
//                if (body != null) {
//                    Log.v("data",body)
//                }
//                else{
//                    Log.v("data","empty")
//                }
//                val jsonObject= JSONObject(body)
//                val jsonArray: JSONArray =jsonObject.getJSONArray("choices")
//                val textResult=jsonArray.getJSONObject(0).getString("text")
                Log.e("errrr",body.toString())
                callback(body!!)

            }
        })
    }




}