package julotestcase.sanjaya.network.di

import com.pluto.plugins.network.PlutoInterceptor
import com.skydoves.sandwich.datasource.adapters.DataSourceCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.common.utils.initializer.DebuggerInitializer
import julotestcase.sanjaya.network.domain.BaseUrlConfigUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

/**
 * A module containing network dependencies used throughout the application.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModules {
    /**
     * Provides an OkHttpClient instance with Pluto and Flipper interceptors, and a timeout of 1 minute for
     * connect, read, and write operations.
     *
     * @return the OkHttpClient instance
     */
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(PlutoInterceptor())
        .addNetworkInterceptor(DebuggerInitializer.getFlipperInterceptor())
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    /**
     * Provides a Retrofit instance configured with a custom DataSourceCallAdapterFactory and an OkHttpClient instance
     * provided by the `providesOkHttpClient()` method. The base URL is determined by the `BaseUrlConfigUseCase` class,
     * which is injected as a parameter. If the `BaseUrlConfigUseCase` returns an empty or null value, an empty string
     * will be used as the base URL.
     *
     * @param okHttpClient the OkHttpClient instance to use for network requests
     * @param baseUrlConfigUseCase the UseCase to determine the base URL to use for network requests
     * @return the Retrofit instance
     */
    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        baseUrlConfigUseCase: BaseUrlConfigUseCase
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(DataSourceCallAdapterFactory.create())
        .baseUrl(runBlocking {
            baseUrlConfigUseCase.getBaseUrl().firstOrNull().orEmpty()
        })
        .client(okHttpClient)
        .build()
}
