package com.example.mygit

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.row_layout.*


class MainActivity : AppCompatActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://api.github.com/repositories"
        AsyncTaskHandleJson().execute(url)
        Git_list.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->

            val str: String = Git_url.text.toString()
            Toast.makeText(this, str,Toast.LENGTH_SHORT).show()
            var intent = Intent(this,WebActivity::class.java)
            intent.putExtra("str",str)
            startActivity(intent)
            finish()
        }
    }

    inner class AsyncTaskHandleJson : AsyncTask<String,String,String>() {
        override fun doInBackground(vararg url: String?): String {
          var text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use {it.reader().use{reader-> reader.readText()}}
            }finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }

        private fun handleJson(jsonString: String?) {
            val jsonArray = JSONArray(jsonString)
            val list = ArrayList<Git>()
            var x = 0
            while (x< jsonArray.length()){
                   val jsonObject = jsonArray.getJSONObject(x)
                list.add(Git(
                    jsonObject.getInt("id"),
                    jsonObject.getString("full_name"),
                    jsonObject.getString("name"),
                    jsonObject.getString("description"),
                    jsonObject.getString("html_url")
                ))
                x++
            }
           val adapter = ListAdapte(this@MainActivity,list)
               Git_list.adapter= adapter
        }


    }



}






















