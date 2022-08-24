package com.android.floward.arch.network

import com.android.floward.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

class DefaultApiClient() : ApiClient {

  private val okHttpClient: OkHttpClient = buildOkHttpClient()
  private val retrofit by lazy { buildRetrofit(okHttpClient) }

  private val services: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()

  private fun buildOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(timeoutInterceptor())
      .addInterceptor(loggingInterceptor())
      .readTimeout(60, TimeUnit.SECONDS)
      .connectTimeout(60, TimeUnit.SECONDS).build()
  }

  private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(getMoshiConverter())
      .baseUrl(Constants.BASE_URL).build()
  }

  private fun getMoshiConverter(): Converter.Factory {
    val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
    return MoshiConverterFactory.create(moshi)
  }

  private fun loggingInterceptor(): Interceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    val logLevel = if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor.Level.BODY
    } else {
      HttpLoggingInterceptor.Level.NONE
    }
    loggingInterceptor.level = logLevel
    return loggingInterceptor
  }

  private fun timeoutInterceptor(): Interceptor {
    return Interceptor { chain ->
      val request = chain.request()

      var connectTimeout = chain.connectTimeoutMillis()
      var readTimeout = chain.readTimeoutMillis()
      var writeTimeout = chain.writeTimeoutMillis()

      val connectNew = request.header(CONNECT_TIMEOUT)
      val readNew = request.header(READ_TIMEOUT)
      val writeNew = request.header(WRITE_TIMEOUT)

      if (!connectNew.isNullOrEmpty()) {
        connectTimeout = Integer.valueOf(connectNew)
      }
      if (!readNew.isNullOrEmpty()) {
        readTimeout = Integer.valueOf(readNew)
      }
      if (!writeNew.isNullOrEmpty()) {
        writeTimeout = Integer.valueOf(writeNew)
      }

      val builder = request.newBuilder()
      builder.removeHeader(CONNECT_TIMEOUT)
      builder.removeHeader(READ_TIMEOUT)
      builder.removeHeader(WRITE_TIMEOUT)

      chain.withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
        .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
        .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS).proceed(builder.build())
    }
  }

  @Suppress("UNCHECKED_CAST")
  override fun <T> getService(cls: Class<T>): T {
    if (!services.contains(cls)) {
      services.putIfAbsent(cls, retrofit.create(cls)!!)
    }
    return services[cls] as T
  }

  companion object {
    const val IS_AUTHORIZABLE = "IsAuthorizable"
    const val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
    const val READ_TIMEOUT = "READ_TIMEOUT"
    const val WRITE_TIMEOUT = "WRITE_TIMEOUT"

    @Volatile
    private var INSTANCE: ApiClient? = null

    @Synchronized
    @JvmStatic
    fun getInstance(): ApiClient {
      val localInstance1 = INSTANCE
      if (localInstance1 != null) {
        return localInstance1
      }

      return synchronized(this) {
        val localInstance2 = INSTANCE
        if (localInstance2 != null) {
          localInstance2
        } else {
          val localInstance3 = DefaultApiClient()
          INSTANCE = localInstance3
          localInstance3
        }
      }
    }
  }
}
