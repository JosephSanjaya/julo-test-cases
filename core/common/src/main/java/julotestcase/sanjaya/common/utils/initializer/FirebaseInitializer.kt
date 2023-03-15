package julotestcase.sanjaya.common.utils.initializer

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.perf.ktx.performance
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

/**
 * Initializer class for initializing Firebase services and setting up remote config
 */
class FirebaseInitializer : Initializer<Unit> {
    /**
     * Creates and initializes Firebase services and sets up remote config
     *
     * @param context Context object to initialize Firebase with
     */
    override fun create(context: Context) {
        Firebase.initialize(context)
        Firebase.crashlytics
        Firebase.performance
        Firebase.remoteConfig.run {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = MINIMUM_FETCH_INTERVAL
            }
            setConfigSettingsAsync(configSettings)
        }
    }

    /**
     * Returns a list of classes that this initializer depends on
     *
     * @return A mutable list of class objects
     */
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
    companion object {
        private const val MINIMUM_FETCH_INTERVAL =
            600L  // Minimum fetch interval for remote config in seconds
    }
}
