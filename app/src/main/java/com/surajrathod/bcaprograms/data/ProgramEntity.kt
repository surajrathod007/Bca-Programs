package com.surajrathod.bcaprograms.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "program_table")
data class ProgramEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val content : String,
    val sem : String,
    val sub : String,
    val unit : String

)