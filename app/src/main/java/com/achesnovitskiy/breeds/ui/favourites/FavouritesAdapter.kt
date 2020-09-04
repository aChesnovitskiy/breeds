package com.achesnovitskiy.breeds.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.achesnovitskiy.breeds.R
import com.achesnovitskiy.breeds.ui.dto.Breed
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breed.*
import java.util.*

class FavouritesAdapter(private val onItemClickListener: (Breed) -> Unit) :
    ListAdapter<Breed, FavouritesViewHolder>(
        FavouritesDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder =
        FavouritesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_breed,
                    parent,
                    false
                )
        )

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }
}

class FavouritesDiffCallback : DiffUtil.ItemCallback<Breed>() {

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

class FavouritesViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    @ExperimentalStdlibApi
    fun bind(breed: Breed, onItemClickListener: (Breed) -> Unit) {

        breed_name_text_view.text = breed.name.capitalize(Locale.getDefault())

        itemView.setOnClickListener {
            onItemClickListener(breed)
        }
    }
}