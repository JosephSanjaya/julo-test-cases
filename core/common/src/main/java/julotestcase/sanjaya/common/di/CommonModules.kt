package julotestcase.sanjaya.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.common.data.pref.PrefRepo
import javax.inject.Named
import javax.inject.Singleton

/**
 * A module containing common dependencies used throughout the application.
 */
@Module
@InstallIn(SingletonComponent::class)
class CommonModules {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PrefRepo.PREF_NAME)

    /**
     * Provides an instance of [FirebaseRemoteConfig].
     *
     * @return An instance of [FirebaseRemoteConfig].
     */
    @Provides
    @Singleton
    fun providesRemoteConfigClient(): FirebaseRemoteConfig = Firebase.remoteConfig

    /**
     * Provides an instance of [Gson] with the following configuration:
     * - Lenient parsing to ignore syntax errors.
     * - Excludes fields that are not annotated with [Expose].
     * - Serializes null values.
     *
     * @return An instance of [Gson] with the specified configuration.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .excludeFieldsWithoutExposeAnnotation()
        .serializeNulls()
        .create()

    /**
     * Provides a [DataStore] instance for the app's common preferences.
     * Uses the [ApplicationContext] to access the app's context and retrieve the data store.
     *
     * @param context The [ApplicationContext] used to retrieve the data store.
     * @return [DataStore] instance for the app's common preferences.
     * **/
    @Provides
    @Singleton
    @Named(PrefRepo.PREF_NAME)
    fun provideCommonPref(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.dataStore
}
