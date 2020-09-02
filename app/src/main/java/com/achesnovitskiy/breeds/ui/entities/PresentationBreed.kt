package com.achesnovitskiy.breeds.ui.entities

data class PresentationBreed(
    val name: String,
    val subbreeds: List<PresentationBreed>?,
    val isFavourite: Boolean
)