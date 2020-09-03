package com.achesnovitskiy.breeds.domain


import com.achesnovitskiy.breeds.data.api.Api
//import com.achesnovitskiy.breeds.data.db.Db
import io.reactivex.Observable
import javax.inject.Inject

interface Repository {
//    val breedsObservable: Observable<List<Breed>>
    val breedsObservable: Observable<String>
}

class RepositoryImpl @Inject constructor(
    private val api: Api/*,
    private val db: Db*/
) : Repository {

    override val breedsObservable: Observable<String>
        get() = api.getBreeds()
            .map { breedsResponse ->
                breedsResponse
                    .message
                    .toString()
            }
}