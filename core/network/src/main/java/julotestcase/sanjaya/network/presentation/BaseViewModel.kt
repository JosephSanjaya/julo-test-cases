package julotestcase.sanjaya.network.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import retrofit2.HttpException
import timber.log.Timber

import java.net.SocketTimeoutException

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * A base view model class that provides state management utilities.
 */
open class BaseViewModel : ViewModel() {
    /**
     * A hash map of String keys and MutableSharedFlow values that is used to store
     * every state that is saved in the view model.
     */
    val stateRegistry = hashMapOf<String, MutableSharedFlow<Any>>()

    /**
     * The defaultError variable is declared using
     * the by state delegate and is a state object of type NetworkError
     * this error state will used if viewmodel use executeRoutine with param
     * useDefaultError true
     *
     * @see executeRoutine
     */
    val defaultError by state<Throwable>(DEFAULT_ERROR_KEY)

    /**
     * The defaultLoading variable is declared using
     * the by state delegate and is a state object of type NetworkError
     * this error state will used if viewmodel use executeRoutine with param
     * useDefaultLoading true
     *
     * @see executeRoutine
     */
    val defaultLoading by state<Boolean>(DEFAULT_LOADING_KEY)

    /**
     * Creates or sets the state with the given `key` and `value`
     * in the `stateRegistry` map.
     *
     * @param key the key to use for the state.
     * @param value the initial value for the state.
     * @param fallback a function to handle any exceptions that occur while
     * creating or setting the state.
     */
    inline fun <reified T> createOrSetState(
        key: String,
        value: T? = null,
        fallback: (Throwable) -> Unit = {
            Firebase.crashlytics.recordException(it)
            Timber.e(it)
        }
    ) {
        runCatching {
            if (stateRegistry.containsKey(key) && value != null) {
                stateRegistry[key]?.tryEmit(value as Any)
            } else {
                stateRegistry[key] = MutableSharedFlow<Any>(
                    replay = 1,
                    onBufferOverflow = BufferOverflow.DROP_OLDEST,
                ).apply {
                    value?.let {
                        tryEmit(value)
                    }
                }
            }
        }.onFailure(fallback)
    }

    /**
     * Returns a lazy `SharedFlow` with the given `key` and `default` value.
     *
     * @param key the key to use for the state.
     * @param default the default value for the state.
     * @return Returns a lazy `SharedFlow` instance.
     */
    inline fun <reified T> state(key: String, default: T? = null): Lazy<SharedFlow<T>> = lazy(LazyThreadSafetyMode.NONE) {
        @Suppress("UNCHECKED_CAST")
        if (stateRegistry.containsKey(key)) {
            stateRegistry[key]!!
        } else {
            createOrSetState<T>(key, default)
            stateRegistry[key]!!
        } as SharedFlow<T>
    }

    /**
     *
     * Executes a routine in the `viewModelScope` with the given `key`
     * and returns a `Job`.
     *
     * The routine is executed in the `Dispatchers.IO` dispatcher
     * and updates the states in the
     * `stateRegistry` map.
     *
     * @param key The unique key to identify the state that is being updated.
     * @param useDefaultLoading Whether to use the default loading state or not.
     * @param useDefaultError Whether to use the default error state or not.
     * @param execute The suspend function that is executed as part of the routine.
     * @return Returns a `Job` instance.
     **/
    inline fun <reified T> executeRoutine(
        key: String,
        useDefaultLoading: Boolean = true,
        useDefaultError: Boolean = true,
        crossinline execute: suspend () -> T
    ): Job = viewModelScope.launch(Dispatchers.IO) {
        val failureFallback: (Throwable) -> Unit = { ex ->
            createOrSetState(
                if (useDefaultError) DEFAULT_ERROR_KEY else key,
                ex
            )
            @Suppress("KotlinConstantConditions")
            if (ex !is HttpException ||
                    ex !is CancellationException ||
                    ex !is SocketTimeoutException
            ) {
                Firebase.crashlytics.recordException(ex)
                Timber.e(ex)
            }
            createOrSetState(
                if (useDefaultLoading) {
                    DEFAULT_LOADING_KEY
                } else {
                    key
                }, false
            )
        }
        runCatching {
            // Set Loading State and Clear Error State
            createOrSetState(
                if (useDefaultLoading) DEFAULT_LOADING_KEY else key,
                true
            )
            createOrSetState(
                if (useDefaultError) DEFAULT_ERROR_KEY else key,
                Throwable()
            )
            createOrSetState(key, execute(), failureFallback)
        }.onSuccess {
            createOrSetState(
                if (useDefaultLoading) DEFAULT_LOADING_KEY else key,
                false
            )
        }.onFailure(failureFallback)
    }

    override fun onCleared() {
        super.onCleared()
        stateRegistry.clear()
    }

    companion object {
        const val DEFAULT_ERROR_KEY = "default_error"
        const val DEFAULT_LOADING_KEY = "default_loading"
    }
}
