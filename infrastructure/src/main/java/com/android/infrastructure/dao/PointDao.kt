package com.android.infrastructure.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.infrastructure.entities.Point

@Dao
interface PointDao {

    @Query("SELECT * FROM point")
    fun getPoints(): List<Point>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePoint(point: Point): Long
}