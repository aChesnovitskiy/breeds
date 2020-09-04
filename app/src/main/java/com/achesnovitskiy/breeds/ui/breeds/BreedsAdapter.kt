package com.achesnovitskiy.breeds.ui.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.achesnovitskiy.breeds.R
import com.achesnovitskiy.breeds.ui.dto.Breed
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breed.*
import java.util.*

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

    @ExperimentalStdlibApi
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

    @ExperimentalStdlibApi
    fun bind(breed: Breed, onItemClickListener: (Breed) -> Unit) {

        breed_name_text_view.text = breed.name.capitalize(Locale.getDefault())

        if (breed.subbreeds.isNotEmpty()) {
            breed_subbreeds_text_view.isVisible = true

            breed_subbreeds_text_view.text = if (breed.subbreeds.size == 1) {
                containerView.context.getString(R.string.one_subbreed)
            } else {
                containerView.context.getString(R.string.f_subbreeds, breed.subbreeds.size)
            }
        }

        itemView.setOnClickListener {
            onItemClickListener(breed)
        }
    }
}