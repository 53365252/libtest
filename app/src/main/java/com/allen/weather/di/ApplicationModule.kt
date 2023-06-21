package com.allen.weather.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import com.allen.weather.BuildConfig
import com.allen.weather.app.MyInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule {

    companion object {
        private const val SHARED_PREFERENCES = "base_shared_preferences"
        private const val BASE_URL = "https://api.darksky.net/"
        private const val HTTP_READ_TIMEOUT = 10*1000L
        private const val HTTP_CONNECT_TIMEOUT = 10*1000L
    }

    @Provides
    fun providesResources(context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun providesCache(context: Context): Cache =
        Cache(context.cacheDir, 1024)

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder().cache(cache)
            .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .hostnameVerifier()
//            .addInterceptor(MyInterceptor())
//                .dns(
//                        object: Dns{
//                            override fun lookup(hostname: String): List<InetAddress> {
//                                InetAddress.getAllByName(hostname)
//                            }
//
//                        }
//                )
            .apply {
                val s = "abc".toCharArray()
                if(BuildConfig.DEBUG){
                    addInterceptor( HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                        override fun log(s: String) {
                            Log.e("sample", s)
                        }
                    }))
                }
            }
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL).build()

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
}

