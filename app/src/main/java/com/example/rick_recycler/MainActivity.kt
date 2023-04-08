package com.example.rick_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var rickList: MutableList<String>
    private lateinit var rvRicks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvRicks = findViewById(R.id.rick_list)
        rickList = mutableListOf()
        getRickImageURL()
    }

    private fun getRickImageURL() {
        val client = AsyncHttpClient()
        client["https://rickandmortyapi.com/api/character", object: JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {

                val rickResults = json.jsonObject.getJSONArray("results")
                for (i in 0 until rickResults.length()) {
                    rickList.add(rickResults.getJSONObject(i).getString("image"))
                    rickList.add(rickResults.getJSONObject(i).getString("name"))
                    rickList.add(rickResults.getJSONObject(i).getString("species"))
                }
                Log.d("rickImageURL", "rick image URL set")
                Log.d("rick", "response successful")
                val adapter = RickAdapter(rickList)
                rvRicks.adapter = adapter
                rvRicks.layoutManager = LinearLayoutManager(this@MainActivity)
            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Rick Error", errorResponse)
            }
    }]
}}
