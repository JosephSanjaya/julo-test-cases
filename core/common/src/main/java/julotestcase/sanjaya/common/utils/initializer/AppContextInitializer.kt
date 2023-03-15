package julotestcase.sanjaya.common.utils.initializer

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.Service
import android.app.backup.BackupAgent
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.ContextThemeWrapper
import androidx.startup.Initializer

/**
 * **WARNING!** Please, do not use this context if you rely on a scoped [Context] such as accessing
 * themed resources from an [Activity] or a [ContextThemeWrapper].
 *
 * This [Context] which by default is an instance of your [Application] class is initialized before
 * [Application.onCreate] is called (but after your [Application]'s constructor has run) thanks to
 * AndroidX App Startup which is initialized by Android as early as possible when your app's
 * process is created.
 */
val appContext: Context get() = internalCtx ?: internalCtxUninitialized()

@SuppressLint("StaticFieldLeak")
private var internalCtx: Context? = null

/**
 * An implementation of [androidx.startup.Initializer] that initializes the app context by
 * calling [Context.injectAsAppContext] on the given [context].
 * This initializer is intended to be registered in the AndroidManifest.xml file using
 * the <meta-data> tag, with a name attribute set to the fully qualified class name of this initializer.
 **/
class AppContextInitializer : Initializer<Context> {
    override fun create(context: Context): Context {
        context.injectAsAppContext()
        return appContext
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}

/**
 * **Usage of this method is discouraged** because the [appContext]
 * should be automatically initialized
 * and setting it to a custom Context may cause unpredictable behavior in some parts of the app,
 * especially if some libraries rely on the true applicationContext.
 *
 * **However**, if your app needs to use [appContext] outside of the default process
 * and the reflection
 * method throws an exception on some devices, you may find useful to call this method from your
 * [Application.onCreate]. This method may also be useful if you need change
 * the [appContext] value with
 * one from a configuration context with an overriden locale for example.
 *
 * **If you ever use this method because of non-default process, and throwing reflection init,
 * be sure to call this method before you try to access [appContext].**
 *
 * Note that the input [Context] is checked for memory leak ability, and will throw if you try to
 * pass an [Activity], a [Service], a [BackupAgent], a [Context] from another app or
 * a [ContextWrapper] based on these classes.
 *
 * @see appContext
 * @see canLeakMemory
 * @see Context.createConfigurationContext
 */
fun Context.injectAsAppContext() {
    require(!canLeakMemory()) { "The passed Context($this) would leak memory!" }
    internalCtx = this
}

/**
 * This method will return true on [Context] implementations known to be able to leak memory.
 * This includes [Activity], [Service], the lesser used and lesser known [BackupAgent], as well as
 * any [ContextWrapper] having one of these as its base context.
 */
fun Context.canLeakMemory(): Boolean = when (this) {
    is Application -> false
    is Activity, is Service, is BackupAgent -> true
    is ContextWrapper -> if (baseContext === this) true else baseContext.canLeakMemory()
    else -> applicationContext === null
}

/**
 * Unbinds the current [appContext] by setting the internal context reference to null.
 * After calling this function, the [appContext] will throw an error if accessed
 * and not initialized again.
 * @see appContext
 **/
fun unbindAppContext() {
    internalCtx = null
}

private fun internalCtxUninitialized(): Nothing {
    val processName = getProcessName()
    val isDefaultProcess = ':' !in processName
    val (cause: String, solutions: List<String>) = when {
        isDefaultProcess -> "App Startup didn't run" to listOf(
            "If App Startup has been disabled, enable it back in the " +
                    "AndroidManifest.xml file of the app.",
            "For other cases, call injectAsappContext() in the app's Application " +
                    "subclass in its initializer or in its onCreate function."
        )
        else -> "App Startup is not enabled for non default processes" to listOf(
            "Call injectAsappContext() in the app's Application subclass in " +
                    "its initializer or in its onCreate function."
        )
    }
    error(buildString {
        appendLine("appContext has not been initialized!")
        when (solutions.size) {
            1 -> appendLine("Possible solution: ${solutions.single()}")
            else -> {
                appendLine("$cause. Possible solutions:")
                solutions.forEachIndexed { index, solution ->
                    append(index + 1)
                    append(". ")
                    append(solution)
                }
            }
        }
    })
}

@SuppressLint("PrivateApi", "ObsoleteSdkInt")
private fun getProcessName(): String {
    if (Build.VERSION.SDK_INT >= 28) {
        return Application.getProcessName()
    }
    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    val activityThread = Class.forName("android.app.ActivityThread")
    // Before API 18, the method was incorrectly named "currentPackageName",
    // but it still returned the process name
    // See https://github.com/aosp-mirror/platform_frameworks_base/commit/b57a50bd16ce25db441da5c1b63d48721bb90687
    val methodName = if (Build.VERSION.SDK_INT >= 18) "currentProcessName" else "currentPackageName"
    return activityThread.getDeclaredMethod(methodName).invoke(null) as String
}
