package com.example.recipeapp
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.example.recipeapp.databinding.ItemRowBinding

class RecipeAdapter(private val recipeList: ArrayList<RecipeItem>): RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>() {

        class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                ItemRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = recipeList[position]
            holder.binding.apply {
                titleTv.text = item.title
                authorTv.text = item.author
                ingredientTv.text = item.ingredient
                instructionsTv.text = item.instructions
            }
        }

        override fun getItemCount() = recipeList.size
    }

