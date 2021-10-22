package edu.fullerton.ecs.mdap.intersectiondb

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import edu.fullerton.ecs.mdap.intersectiondb.database.Intersection
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao
import kotlinx.coroutines.launch

class IntersectionViewModel(
    val database: IntersectionDao,
    application: Application) : AndroidViewModel(application) {

    var _name = MutableLiveData("")
    var _location = MutableLiveData("")

    val name: LiveData<String>
        get() {
            return _name
        }

    val location: LiveData<String>
        get() {
            return _location
        }

    val intersectionList = database.getAllIntersections()

    var intersectionString = Transformations.map(intersectionList) {
        intersections ->
        Log.i("IS", "reading data?")
        var result = ""
        for (intersection in intersections) {
            result += "${intersection.name} @ ${intersection.location}\n"
        }
        result
    }

    fun insert() {
        Log.i("IVM", "Launching")
        viewModelScope.launch {
            var intersection = Intersection()
            intersection.name = _name.value.toString()
            intersection.location = _location.value.toString()

            database.insert(intersection)
            Log.i("IVM", "inserted")
        }

    }

    fun clear() {
        viewModelScope.launch {
            database.clear()
        }
    }
}