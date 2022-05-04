package com.android.infrastructure.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "point")
class Point(@PrimaryKey(autoGenerate = true) val id: Long,
            @ColumnInfo(name = "point_name") val pointName: String,
            @ColumnInfo(name = "type") val type: String,
            @ColumnInfo(name = "note") val note: String
            ) {
}