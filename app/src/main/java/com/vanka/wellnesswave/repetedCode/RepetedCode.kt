package com.vanka.wellnesswave.repetedCode

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import okhttp3.*
import java.io.IOException

class RepetedCode {
    fun intentFragment(id:Int, fragment: Fragment, context: Context){
        val load = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        load.replace(id,fragment)
        load.commit()
    }
    fun callOpenAIAPI(apiKey: String, prompt: String) {
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
                // Handle API response
                println(responseBody)
            }
        })
    }

}