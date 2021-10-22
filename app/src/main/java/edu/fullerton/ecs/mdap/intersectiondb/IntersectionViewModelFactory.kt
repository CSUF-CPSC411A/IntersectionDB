package edu.fullerton.ecs.mdap.intersectiondb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao

class IntersectionViewModelFactory(
    private val dataSource: IntersectionDao,
    private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntersectionViewModel::class.java)) {
            return IntersectionViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}