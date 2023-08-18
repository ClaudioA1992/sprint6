package cl.awakelab.sprint6.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.LiveData
import cl.awakelab.sprint6.R
import cl.awakelab.sprint6.data.local.PhoneDao
import cl.awakelab.sprint6.data.local.entities.PhoneDetailEntity
import cl.awakelab.sprint6.data.local.entities.PhoneEntity
import cl.awakelab.sprint6.data.remote.PhoneAPI

class Repository(private val phoneAPI: PhoneAPI, private val phoneDAO: PhoneDao) {

    fun getPhonesEntity(): LiveData<List<PhoneEntity>> = phoneDAO.selectPhones()

    fun getPhoneDetail(id: Int): LiveData<PhoneDetailEntity> = phoneDAO.selectPhoneDetail(id)

    suspend fun getPhones(context: Context) {
        try {
            val response = phoneAPI.getAllPhones()
            if (response.isSuccessful) {
                val response = response.body()!!
                response.let {
                    val phoneEntities = it.map { it.transformToEntity(it) }
                    phoneDAO.insertPhones(phoneEntities)
                }
            }
        } catch(exception: Exception) {
            Log.e("getPhones error", exception.toString())
            Toast.makeText(context, R.string.disconnected_phones, Toast.LENGTH_LONG).show()
        }
    }

    suspend fun getDetail(id: Int, context: Context) {
        try {
            val response = phoneAPI.getDetail(id)
            print("Get detail")
            if (response.isSuccessful) {
                val response = response.body()!!
                Log.d("Response for detail in repository", response.toString())
                response.let {
                    val phoneDetailEntity = it.transformToDetailEntity(it)
                    phoneDAO.insertPhoneDetail(phoneDetailEntity)
                }
            }
        } catch(exception: Exception) {
            Log.e("getDetail error", exception.toString())
            Toast.makeText(context, R.string.disconnected_detail, Toast.LENGTH_LONG).show()
        }
    }

}

