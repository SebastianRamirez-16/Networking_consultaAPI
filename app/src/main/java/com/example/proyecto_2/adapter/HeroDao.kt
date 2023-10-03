package com.example.proyecto_2.adapter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_2.models.Hero

@Dao
interface HeroDao {
// Room trabaja con list y cursores, no con ArrayList

    @Query("SELECT * FROM Hero")
    fun getAll(): List<Hero>

    @Insert
    fun insert(vararg heroes: Hero)

    @Delete
    fun delete(vararg heroes: Hero)

    @Update
    fun update(vararg heroes: Hero)

    /*
    @Query("SELECT * FROM Hero WHERE name = :name")
    fun getHeroByName(name: String): Hero?*/
}