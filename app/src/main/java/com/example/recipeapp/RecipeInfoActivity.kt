package com.example.recipeapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_info)
        val titleEditText = findViewById<EditText>(R.id.title_et)
        val authorEditText = findViewById<EditText>(R.id.author_et)
        val ingEditText = findViewById<EditText>(R.id.ingredient_et)
        val insEditText = findViewById<EditText>(R.id.instructions_et)

        val saveButton = findViewById<Button>(R.id.save_btn)
        saveButton.setOnClickListener {
            insertRecipe(RecipeItem(titleEditText.text.toString(),
                authorEditText.text.toString(),
                ingEditText.text.toString(),insEditText.text.toString(),0))
        }
       val  btCancel = findViewById<Button>(R.id.cancel_btn)
        btCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val viewButton = findViewById<Button>(R.id.view_btn)
        viewButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            recreate()
        }
    }

    private fun insertRecipe(item: RecipeItem){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.addItem(item)?.enqueue(object: Callback<RecipeItem?> {
            override fun onResponse(call: Call<RecipeItem?>, response: Response<RecipeItem?>) {
                Toast.makeText(this@RecipeInfoActivity, "User added",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RecipeItem?>, t: Throwable) {
                Toast.makeText(this@RecipeInfoActivity, "Something went wrong, please try again",
                    Toast.LENGTH_SHORT).show()
                call.cancel()
            }

        })

    }
}