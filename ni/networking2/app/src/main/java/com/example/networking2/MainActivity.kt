package com.example.networking2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val originalList= arrayListOf<User>()
    val adapter=UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.onItemClick={
            val intent=Intent(this,UserActivity::class.java)
            intent.putExtra("ID",it)
            startActivity(intent)
         }
       userRv.apply {
           layoutManager=LinearLayoutManager(this@MainActivity)
           adapter=this@MainActivity.adapter
       }
        searchview.isSubmitButtonEnabled=true
        searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{searchUsers(it)}
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let{searchUsers(it)}
                return true
            }

        })

        searchview.setOnCloseListener {
            adapter.swapData(originalList)
            true

        }
        GlobalScope.launch(Dispatchers.Main){

       val response= withContext(Dispatchers.IO) {
           Client.api.getUsers()
       }
            if(response.isSuccessful){
            response.body()?.let{
            originalList.addAll(it)
                adapter.swapData(it)
            }
            }
        }


        }
fun searchUsers(query:String){
    GlobalScope.launch(Dispatchers.Main){

        val response= withContext(Dispatchers.IO) {
            Client.api.searchUsers(query)
        }
        if(response.isSuccessful){
            response.body()?.let{
                it.items?.let { it1 -> adapter.swapData(it1) }
            }
        }
    }
}

}