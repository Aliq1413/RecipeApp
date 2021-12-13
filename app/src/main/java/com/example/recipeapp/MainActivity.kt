package com.example.recipeapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var recyclerView: RecyclerView
lateinit var recipeList: ArrayList<RecipeItem>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addItemButton = findViewById<Button>(R.id.new_Item_btn)
        addItemButton.setOnClickListener {
            startActivity(Intent(this, RecipeInfoActivity::class.java))
            finish()
        }

        recipeList = arrayListOf()
        setItemFromAPI()
        Log.d("users", "After setting $recipeList")

        recyclerView = findViewById(R.id.item_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setItemFromAPI() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<Recipes?>? = apiInterface!!.getItem()
        call?.enqueue(object: Callback<Recipes?>{
            override fun onResponse(call: Call<Recipes?>, response: Response<Recipes?>) {
                recipeList = response.body()!!
                recyclerView.adapter = RecipeAdapter(recipeList)
            }
            override fun onFailure(call: Call<Recipes?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong, please try again",
                    Toast.LENGTH_SHORT).show()
                call.cancel()
            }
        })
    }

}