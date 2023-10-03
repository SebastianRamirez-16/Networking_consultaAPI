package com.example.proyecto_2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto_2.adapter.HeroDao
import com.example.proyecto_2.models.Hero

@Database(entities = arrayOf(Hero::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    // usamos el patron singleton, para no abrir multiples instancias de la base de datos
    abstract fun getDao(): HeroDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        // creamos la base de datos
        fun getInstance(context: Context):AppDatabase{
            if(INSTANCE == null){
                // si no existe la BD, la creamos
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "Heroes.db")
                    .allowMainThreadQueries()
                    .build()
            }

            // si existe la BD, la retornamos
            return INSTANCE!!
        }
    }
}