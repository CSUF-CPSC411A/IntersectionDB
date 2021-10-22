package edu.fullerton.ecs.mdap.intersectiondb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intersection_table")
data class Intersection(
    @PrimaryKey(autoGenerate = true)
    var intersectionId: Long = 0L,

    @ColumnInfo()
    var name: String = "",

    @ColumnInfo()
    var location: String = ""
)
