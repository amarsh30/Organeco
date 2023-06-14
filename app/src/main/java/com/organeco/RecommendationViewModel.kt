package com.organeco

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RecommendationViewModel (application: Application) : ViewModel() {
    private val mRecommendationRepository : RecommendationRepository = RecommendationRepository(application)

    fun insert (recommendation: Recommendation){
        mRecommendationRepository.insert(recommendation)
    }

    fun delete(recommendation: Recommendation) {
        mRecommendationRepository.delete(recommendation)
    }

    fun getAllRecommendation(): LiveData<List<Recommendation>> = mRecommendationRepository.getAllRecommendation()
}