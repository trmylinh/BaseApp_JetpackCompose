package com.example.baseapp_jetpackcompose.di.module

import android.content.Context
import androidx.compose.runtime.Stable
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.baseapp_jetpackcompose.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/*
@Module: Annotation này để đánh dấu 1 lớp là Module trong Hitl -> nơi định nghĩa cách tạo ra các phụ thuộc mà Hilt không thể tự động khởi tạo thông qua @Inject
@InstallIn(SingletonComponent::class): nơi chỉ định lifetime của các phụ thuộc được cung cấp bởi module này, SingletonComponent có nghĩa là các phụ thuộc được cung cấp sẽ sống trong suốt vòng đời của ứng dụng, tức là chỉ được tạo một lần (singleton).
* */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /*
    @Provides: chỉ định hàm cung cấp đối tượng phụ thuộc. Hilt sẽ sử dụng hàm này khi cần cung cấp đố tượng cho các thành phần khác trong ứng dụng.
    @Singleton: Annotation này nói với Hilt rằng đối tượng được cung cấp là một singleton, tức là đối tượng này chỉ được tạo một lần duy nhất và được dùng lại trong suốt vòng đời của ứng dụng.
    * */
    @Provides
    @Singleton
    // hàm này để tạo và cung cấp đối tượng Retrofit đã được cấu hình để có thể gọi API
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        apiUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    // hàm này cung cấp giá trị URL của API
    fun provideApiUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    // Cung cấp đối tượng OkHttpClient để gửi và nhận các yêu cầu HTTP.
    // httpLoggingInterceptor: Ghi log các request HTTP, giúp kiểm tra các request và response.
    // authorizationInterceptor: Một interceptor để thêm (header) cho mỗi request HTTP, thường để xác thực.
   //  chuckInterceptor: Dùng để ghi log các request HTTP và hiển thị chúng ngay trong ứng dụng.
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthInterceptor,
        chuckInterceptor: ChuckerInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(chuckInterceptor)
        .addInterceptor(httpLoggingInterceptor).build()
//    if (BuildConfig.DEBUG) chuckInterceptor else authorizationInterceptor

    @Provides
    @Singleton
    // Cung cấp đối tượng GsonConverterFactory để chuyển đổi JSON thành các đối tượng Kotlin, và ngược lại.
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    // dành cho viêcj debug -> ghi lại các thông tin cơ bản về request và response HTTP
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Provides
    @Singleton
    // ghi log các request HTTP và hiển thị chúng dưới dạng thông báo ứng dụng
    fun provideChuckInterceptor(
        chuckInterceptor: ChuckerCollector,
        @ApplicationContext context: Context,
    ): ChuckerInterceptor = ChuckerInterceptor.Builder(context).collector(chuckInterceptor)
        .maxContentLength(Constants.CONTENT_LENGTH)
        .redactHeaders("Content-Type", "application/json").alwaysReadResponseBody(true).build()

    @Provides
    @Singleton
    // thu thập và hiển thị log của các request HTTP trong ứng dụng.
    fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector =
        ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR,
        )
}

@Stable
// class này để chặn các request HTTP và thêm header vào mỗi request
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Content-Type", "application/json")
        return chain.proceed(requestBuilder.build())
    }
}
