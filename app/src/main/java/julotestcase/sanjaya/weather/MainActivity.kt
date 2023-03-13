package julotestcase.sanjaya.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import julotestcase.sanjaya.weather.ui.theme.WeatherAppTheme

/**
 * MainActivity is the entry point for the WeatherApp application.
 * It extends ComponentActivity and overrides the onCreate method.
 */
class MainActivity : ComponentActivity() {
    /**
     * The onCreate method is called when the activity is starting.
     * It sets the content view to the WeatherAppTheme and creates a Surface
     * with the 'background' color from the theme, which contains the Greeting composable.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

/**
 * The Greeting composable displays a greeting message with the given name.
 * It takes a string parameter 'name', which is used to personalize the greeting.
 *
 * @param name The name of the person to greet.
 */
@Composable
fun greeting(name: String) {
    Text(text = "Hello $name!")
}

/**
 * The DefaultPreview composable is used to preview the Greeting composable in Android Studio's layout editor.
 * It uses the WeatherAppTheme and displays the Greeting composable with the name "Android".
 */
@Preview(showBackground = true)
@Composable
fun defaultPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}
