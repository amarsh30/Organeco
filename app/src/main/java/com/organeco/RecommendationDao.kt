package com.organeco

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecommendationDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(recommendation: Recommendation)

    @Delete
    fun delete(recommendation: Recommendation)

    @Query("SELECT * from recommendation")
    fun getAllRecommendation(): LiveData<List<Recommendation>>
}