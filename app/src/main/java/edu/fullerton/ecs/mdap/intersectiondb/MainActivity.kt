package edu.fullerton.ecs.mdap.intersectiondb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDatabase
import edu.fullerton.ecs.mdap.intersectiondb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val application = requireNotNull(this).application

        val dataSource = IntersectionDatabase.getInstance(application).intersectionDao

        val viewModelFactory = IntersectionViewModelFactory(dataSource, application)

        val intersectionViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(IntersectionViewModel::class.java)

        binding.intersectionViewModel = intersectionViewModel
        binding.lifecycleOwner = this
    }
}