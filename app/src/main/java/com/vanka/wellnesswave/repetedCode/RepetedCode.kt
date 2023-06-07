package com.vanka.wellnesswave.repetedCode

import android.content.Context
import android.widget.TextView
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


}