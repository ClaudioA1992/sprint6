package cl.awakelab.sprint6.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {

    companion object {
        private const val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"
        fun getRetrofitInfo(): PhoneAPI {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(PhoneAPI::class.java)
        }
    }

}