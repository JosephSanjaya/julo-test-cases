package julotestcase.sanjaya.common.di

import com.google.gson.Gson
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * An entry point for accessing [Gson] within the application.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface JsonEntryPoint {
    /**
     * Returns an instance of [Gson].
     *
     * @return An instance of [Gson].
     */
    fun getGson(): Gson
}
