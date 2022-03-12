package com.bd_drmwan.core.module

import android.content.Context
import com.bd_drmwan.core.BuildConfig.*
import com.bd_drmwan.core.main.services.NetworkException
import com.bd_drmwan.core.main.services.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideChuckInterceptor(
//        @ApplicationContext mContext: Context
//    ) = ChuckInterceptor(mContext)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(
        @ApplicationContext mContext: Context
    ): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            if (!NetworkUtil.isConnected(mContext)) {
                throw NetworkException()
            }
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
//        chuckInterceptor: ChuckInterceptor,
        networkInterceptor: Interceptor
    ): OkHttpClient {
        val certificate = CertificatePinner.Builder()
            .add(HOST_NAME, CERTIFICATE_PINNER_1)
            .add(HOST_NAME, CERTIFICATE_PINNER_2)
            .build()
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
//            .addInterceptor(chuckInterceptor)
            .addInterceptor(networkInterceptor)
            .certificatePinner(certificate)
            .build()
    }

    fun buildRetrofit(
        baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}