package com.surajrathod.bcaprograms.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Program (

    @PrimaryKey(autoGenerate = true) //automatic generate primary key
    var id : Int = 0,
    var title : String,
    var content : String,
    var sem : String,
    var sub : String,
    var unit : String
)