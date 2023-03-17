package julotestcase.sanjaya.weather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import julotestcase.sanjaya.weather.R
import julotestcase.sanjaya.weather.databinding.ActivityMainBinding

import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the application.
 *
 * This activity is responsible for displaying the main UI of the application.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
