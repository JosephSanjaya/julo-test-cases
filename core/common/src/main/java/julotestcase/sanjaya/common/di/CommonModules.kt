package julotestcase.sanjaya.common.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A module containing common dependencies used throughout the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object CommonModules {
    /**
     * Provides an instance of [FirebaseRemoteConfig].
     *
     * @return An instance of [FirebaseRemoteConfig].
     */
    @Provides
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
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .excludeFieldsWithoutExposeAnnotation()
        .serializeNulls()
        .create()
}
