package com.example.examgos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM film")
     fun getAll(): List<Film>

    @Query("SELECT * FROM film WHERE uid IN (:filmIds)")
     fun loadAllByIds(filmIds: IntArray): List<Film>

    @Query("SELECT * FROM film WHERE name LIKE :first AND " +
            "year LIKE :last LIMIT 1")
     fun findByName(first: String, last: String): Film

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertAll( film: Film)

    @Delete
     fun delete(film: Film)
}