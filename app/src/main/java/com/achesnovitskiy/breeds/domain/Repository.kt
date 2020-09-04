package com.achesnovitskiy.breeds.domain


import android.util.Log
import com.achesnovitskiy.breeds.data.api.Api
import com.achesnovitskiy.breeds.ui.dto.Breed
//import com.achesnovitskiy.breeds.data.db.Db
import io.reactivex.Observable
import javax.inject.Inject
import kotlin.reflect.KProperty1

interface Repository {
    val breedsObservable: Observable<List<Breed>>
}

class RepositoryImpl @Inject constructor(
    private val api: Api/*,
    private val db: Db*/
) : Repository {

    override val breedsObservable: Observable<List<Breed>>
        get() = api.getBreeds()
            .map { breedsResponse ->
                Log.d("My_Reflection", "${readInstanceProperty<List<String>>(breedsResponse.message, "retriever")}")

                breedsResponse
                    .message
                    .toString()
                    .removePrefix("Message(")
                    .removeSuffix(")")
                    .split(Regex("(=\\[.+?], )|(=\\[.+?])"))
                    .dropLast(1) // FIXME change regex so not to take last empty String
                    .map { breedName ->
                        Breed(
                            name = breedName,
                            subbreeds = readInstanceProperty(breedsResponse.message, breedName),
                            isFavourite = false
                        )
                    }
            }

    @Suppress("UNCHECKED_CAST")
    fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
        val property = instance::class.members
            // don't cast here to <Any, R>, it would succeed silently
            .first { it.name == propertyName } as KProperty1<Any, *>
        // force a invalid cast exception if incorrect type here
        return property.get(instance) as R
    }
}