package com.achesnovitskiy.breeds.ui.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.achesnovitskiy.breeds.R
import com.achesnovitskiy.breeds.ui.entities.Breed
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breed.*

class BreedsAdapter(private val onItemClickListener: (Breed) -> Unit) :
    ListAdapter<Breed, BreedViewHolder>(
        BreedsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder =
        BreedViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_breed,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }
}

class BreedsDiffCallback : DiffUtil.ItemCallback<Breed>() {

    override fun areItemsTheSame(
        oldItem: Breed,
        newItem: Breed
    ): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: Breed,
        newItem: Breed
    ): Boolean =
        oldItem == newItem
}

class BreedViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(breed: Breed, onItemClickListener: (Breed) -> Unit) {

        breed_name_text_view.text = breed.name

        itemView.setOnClickListener {
            onItemClickListener(breed)
        }
    }
}