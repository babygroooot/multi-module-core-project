package com.core.network.di


import com.core.datastore.DataStoreManager
import com.core.network.BuildConfig
import com.core.network.util.NetworkResultCallAdapterFactory
import com.core.network.util.token_manager.TokenAuthenticator
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideBaseURL(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        dataStoreManager: DataStoreManager,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()

        okHttpClientBuilder.callTimeout(20, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(20, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(20, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(20, TimeUnit.SECONDS)
        okHttpClientBuilder.authenticator(tokenAuthenticator)

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.interceptors().add(loggingInterceptor)
        }

        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request()
            val token = runBlocking { dataStoreManager.getToken() }
            val builder = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $token")
            chain.proceed(builder.build())
        }

        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    fun provideAdapterFactory(): CallAdapter.Factory {
        return NetworkResultCallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory,
        adapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }

}