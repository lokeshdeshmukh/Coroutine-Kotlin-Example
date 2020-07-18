package com.example.kotlin_coroutines_simple_example

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Call


class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val visibleTextView=textView
        network_call_button.setOnClickListener(View.OnClickListener {
            coroutineScope.async(Dispatchers.Default) {
                getText(visibleTextView)
            }
        })
    }

    suspend fun getText(textView1: TextView) {
        var text1 = fromUrl1()
        var text2 = fromUrl2()
        coroutineScope.async (Dispatchers.Main)
        {
            textView1.setText(text1+text2)
        }

    }

    suspend fun fromUrl1(): String {
        val service = RetrofitClientInstance.retrofitInstance?.create(GetDataService::class.java)
        service?.apply {
            val call: Call<KotlinDataClass?>? = this.getUrl1
            call?.execute()?.body()?.message?.let {
                return it
            }
        }


        return "call failed"
    }

    suspend fun fromUrl2(): String {
        val service = RetrofitClientInstance.retrofitInstance?.create(GetDataService::class.java)
        val call: Call<KotlinDataClass?>? = service!!.getUrl2
        call?.execute()?.body()?.message?.let {
            return it
        }

        return "call failed"
    }



}