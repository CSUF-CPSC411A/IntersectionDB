package edu.fullerton.ecs.mdap.intersectiondb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IntersectionDao {
    @Insert
    suspend fun insert(intersection: Intersection)

    @Update
    suspend fun update(intersection: Intersection)

    @Query("SELECT * from intersection_table WHERE intersectionId = :key")
    suspend fun get(key: Long): Intersection?

    @Query("DELETE from intersection_table")
    suspend fun clear()

    @Query("SELECT * from intersection_table ORDER BY intersectionId DESC")
    fun getAllIntersections(): LiveData<List<Intersection>>
}