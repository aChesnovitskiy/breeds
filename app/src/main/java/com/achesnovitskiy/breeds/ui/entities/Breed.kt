package com.achesnovitskiy.breeds.ui.entities

data class Breed(
    val name: String,
    val subbreeds: List<String>,
    val isFavourite: Boolean
)