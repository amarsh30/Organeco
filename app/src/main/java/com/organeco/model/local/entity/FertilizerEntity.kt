package com.organeco.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dummy")
class FertilizerEntity(

    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    val id: Int,

    @field:ColumnInfo(name = "name")
    @field:PrimaryKey
    val title: String,

//    @field:ColumnInfo(name = "plantType")
//    val plantType: String,

    @field:ColumnInfo(name = "description")
    val description: String,

    @field:ColumnInfo(name = "image")
    val image: String,

    @field:ColumnInfo(name = "bookmarked")
    var isBookmarked: Boolean
)