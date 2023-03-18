package julotestcase.sanjaya.weather

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * The WeatherApp class is the main application class for the WeatherApp application.
 * It extends the MultiDexApplication class and is annotated with the @AndroidEntryPoint annotation.
 * This annotation is used to enable dependency injection using Hilt.
 */
@HiltAndroidApp
class WeatherApp : MultiDexApplication()
