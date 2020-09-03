package com.achesnovitskiy.breeds.data.api

import io.reactivex.Observable
import retrofit2.http.*

interface Api {

    @GET("breeds/list/all")
    fun getBreeds(): Observable<BreedsResponse>
}