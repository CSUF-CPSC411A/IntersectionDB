package edu.fullerton.ecs.mdap.intersectiondb

import android.app.Application
import androidx.lifecycle.*
import edu.fullerton.ecs.mdap.intersectiondb.database.Intersection
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao
import kotlinx.coroutines.launch

/**
 * IntersectionViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class IntersectionViewModel(
    val database: IntersectionDao, // Data access object for the Intersection entity
    application: Application) : AndroidViewModel(application) {

    var name = MutableLiveData("")
    var location = MutableLiveData("")

    // Retrieves all Intersection objects from the database
    // Represented as a LiveData<List<Intersection>>
    val intersectionList = database.getAllIntersections()

    /**
     * Creates a LiveData<String> that contains information from all Intersection objects.
     * The Transformations.map function takes a LiveData object, performs operations on the
     * object and returns a LiveData-wrapped object.
     */
    var intersectionString = Transformations.map(intersectionList) {
        intersections -> // intersections refer to the underlying data List<Intersection>
        var result = ""
        // Retrieve each Intersection object from the list
        for (intersection in intersections) {
            // Create a string using the Intersection name and location.
            // The intersection string is appended to a longer string with all intersections.
            result += "${intersection.name} @ ${intersection.location}\n"
        }
        // Returns the aggregated String that is wrapped by the map function in a LiveData object.
        result
    }

    /**
     * Inserts the Intersection object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Intersection object using data stored in the EditText views
            var intersection = Intersection()
            intersection.name = name.value.toString()
            intersection.location = location.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(intersection)
        }

    }

    /**
     * Deletes all Intersection entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}