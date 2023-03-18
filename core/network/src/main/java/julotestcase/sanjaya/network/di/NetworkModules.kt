package julotestcase.sanjaya.network.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pluto.plugins.network.PlutoInterceptor
import com.skydoves.sandwich.datasource.adapters.DataSourceCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.common.utils.initializer.DebuggerInitializer
import julotestcase.sanjaya.network.domain.BaseUrlConfigUseCase
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

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
    fun providesOkHttpClient(
        baseUrlUseCase: BaseUrlConfigUseCase
    ): OkHttpClient = RetrofitUrlManager.getInstance().apply {
        putDomain(BaseUrlConfigUseCase.OPEN_WEATHER_BASE_URL, baseUrlUseCase.getOpenWeatherBaseUrl())
        putDomain(BaseUrlConfigUseCase.API_NINJA_BASE_URL, baseUrlUseCase.getApiNinjaBaseUrl())
        setGlobalDomain(baseUrlUseCase.getOpenWeatherBaseUrl())
    }.with(
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
    )
        .addInterceptor(PlutoInterceptor())
        .addNetworkInterceptor(DebuggerInitializer.getFlipperInterceptor())
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
        baseUrlUseCase: BaseUrlConfigUseCase,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrlUseCase.getOpenWeatherBaseUrl())
        .addCallAdapterFactory(DataSourceCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}
