package com.organeco

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "recommendation")
data class Recommendation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "temperature")
    var temperature : Number = 0,

    @ColumnInfo(name = "humidity")
    var humidity : Number = 0,

    @ColumnInfo(name = "moisture")
    var moisture : Number = 0,

    @ColumnInfo(name = "soil_type")
    var soil_type : String? = null,

    @ColumnInfo(name = "crop_type")
    var crop_type : String? = null,

    @ColumnInfo(name = "nitrogen")
    var nitrogen : Number = 0,

    @ColumnInfo(name = "potassium")
    var potassium : Number = 0,

    @ColumnInfo(name = "phosphorous")
    var phosphorous : Number = 0,

    @ColumnInfo(name = "result")
    var result : String? = null


) : Parcelable