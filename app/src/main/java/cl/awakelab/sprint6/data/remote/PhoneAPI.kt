package cl.awakelab.sprint6.data.remote

import cl.awakelab.sprint6.data.remote.models.Phone
import cl.awakelab.sprint6.data.remote.models.PhoneDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneAPI {

    @GET("products")
    suspend fun getAllPhones(): Response<List<Phone>>

    @GET("details/{id}")
    suspend fun getDetail(@Path("id") id: Int): Response<PhoneDetail>

}

