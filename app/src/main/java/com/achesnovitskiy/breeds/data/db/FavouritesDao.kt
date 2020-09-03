package com.achesnovitskiy.breeds.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

//@Dao
//interface FavouritesDao {

//    @Query("SELECT * FROM cat")
//    fun getCats(): Observable<List<Cat>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCats(repos: List<Cat>)
//
//    @Query("DELETE FROM cat")
//    fun clearCats()
//}