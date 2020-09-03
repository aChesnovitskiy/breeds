package com.achesnovitskiy.breeds.ui.entities

data class Breed(
    val name: String,
    val subbreeds: List<Breed>?,
    val isFavourite: Boolean
)