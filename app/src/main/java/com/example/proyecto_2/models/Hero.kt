package com.example.proyecto_2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hero")
class Hero (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var image: String,
)