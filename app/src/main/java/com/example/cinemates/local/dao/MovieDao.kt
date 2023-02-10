package com.example.cinemates.local.dao

import androidx.room.*
import com.example.cinemates.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getById(id: Int): Movie?

    @Query("SELECT * FROM movie WHERE personalStatus=1")
    fun getToSeeMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE personalStatus=0")
    fun getSeenMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE favorite=1")
    fun getFavoriteMovies(): Flow<List<Movie>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND favorite = 1)")
    fun isFavorite(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND personalStatus = 1 )")
    fun isToSee(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND personalStatus = 0)")
    fun isSeen(id: Int): Boolean


}