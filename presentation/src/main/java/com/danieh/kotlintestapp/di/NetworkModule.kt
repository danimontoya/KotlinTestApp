package com.danieh.kotlintestapp.di

import android.content.Context
import com.danieh.domain.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by danieh
 */
@Module
class NetworkModule(private val context: Context, private val url: String, private val debug: Boolean) {

    @Provides
    @Named("prodOkhttpClient")
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {

        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {

            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logInterceptor)
        }

        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRestAdapter(@Named("prodOkhttpClient") okHttpClient: OkHttpClient): Retrofit {

        return retrofit2.Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(com.google.gson.Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Named("unsafeOkhttpClient")
    @Singleton
    internal fun provideUnsafeOkhttpClient(): OkHttpClient {

        /* Trust anything*/
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        val client = OkHttpClient.Builder()
        client.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        client.hostnameVerifier { _, _ -> true }

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        client.addInterceptor(logInterceptor)

        if (!BuildConfig.DEBUG) {
            throw RuntimeException("You fool. Do not use this in production!!!")
        }

        return client.build()
    }
}