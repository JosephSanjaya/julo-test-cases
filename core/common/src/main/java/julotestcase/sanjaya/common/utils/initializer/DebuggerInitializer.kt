package julotestcase.sanjaya.common.utils.initializer

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.blankj.utilcode.util.AppUtils
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptions
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin
import julotestcase.sanjaya.common.BuildConfig
import timber.log.Timber

/**
 * A class that initializes various debugging and monitoring tools when the application is launched.
 */
class DebuggerInitializer : Initializer<Unit> {
    /**
     * Initializes debugging and monitoring tools.
     *
     * @param context The context in which the application is running.
     */
    override fun create(context: Context) {
        initPluto(context as Application)
        SoLoader.init(context, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(context)) {
            AndroidFlipperClient.getInstance(context).apply {
                addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
                addPlugin(CrashReporterPlugin.getInstance())
                addPlugin(DatabasesFlipperPlugin(context))
                addPlugin(NavigationFlipperPlugin.getInstance())
                addPlugin(getNetworkFlipper())
                addPlugin(SharedPreferencesFlipperPlugin(context, AppUtils.getAppPackageName()))
            }.start()
        }
    }

    /**
     * Initializes the Pluto debugging tool and sets exception handlers.
     *
     * @param application The application instance.
     */
    private fun initPluto(application: Application) {
        Pluto.Installer(application)
            .addPlugin(PlutoNetworkPlugin("network"))
            .addPlugin(PlutoExceptionsPlugin("exceptions"))
            .addPlugin(PlutoSharePreferencesPlugin("sharedPref"))
            .addPlugin(PlutoLoggerPlugin("logger"))
            .addPlugin(PlutoRoomsDatabasePlugin("rooms-db"))
            .install()
        PlutoExceptions.setExceptionHandler { thread, throwable ->
            Timber.e(
                "Exception uncaught exception handled on thread: ${thread.name}", throwable
            )
            Firebase.crashlytics.recordException(throwable)
        }
        PlutoExceptions.setANRHandler { thread, exception ->
            Timber.e(
                "ANR potential ANR detected on thread: ${thread.name}", exception
            )
            Firebase.crashlytics.recordException(exception)
        }
    }

    /**
     * Returns a list of classes on which this initializer depends.
     *
     * @return The list of classes on which this initializer depends.
     */
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf(FirebaseInitializer::class.java)

    companion object {
        /**
         * Returns a new instance of the NetworkFlipperPlugin.
         *
         * @return A new instance of the NetworkFlipperPlugin.
         */
        fun getNetworkFlipper() = NetworkFlipperPlugin()

        /**
         * Returns a new instance of the FlipperOkhttpInterceptor.
         *
         * @return A new instance of the FlipperOkhttpInterceptor.
         */
        fun getFlipperInterceptor() = FlipperOkhttpInterceptor(getNetworkFlipper())
    }
}
