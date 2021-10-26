package edu.fullerton.ecs.mdap.intersectiondb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDatabase
import edu.fullerton.ecs.mdap.intersectiondb.databinding.ActivityMainBinding

/**
 * Main interface of the application
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create data binding
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Get reference to this application
        val application = requireNotNull(this).application

        // Retrieve Intersection data access object.
        val dataSource = IntersectionDatabase.getInstance(application).intersectionDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = IntersectionViewModelFactory(dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val intersectionViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(IntersectionViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.intersectionViewModel = intersectionViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this
    }
}