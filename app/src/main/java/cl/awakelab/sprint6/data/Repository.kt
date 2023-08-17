package cl.awakelab.sprint6.data

import androidx.lifecycle.LiveData
import cl.awakelab.sprint6.data.local.PhoneDao
import cl.awakelab.sprint6.data.local.entities.PhoneDetailEntity
import cl.awakelab.sprint6.data.local.entities.PhoneEntity
import cl.awakelab.sprint6.data.remote.PhoneAPI

class Repository(private val phoneAPI: PhoneAPI, private val phoneDAO: PhoneDao) {

    fun getPhonesEntity(): LiveData<List<PhoneEntity>> = phoneDAO.selectPhones()

    fun getPhoneDetail(id: Int): LiveData<PhoneDetailEntity> = phoneDAO.selectPhonesDetail(id)

    suspend fun getPhones() {
        val response = phoneAPI.getAllPhones()
        if(response.isSuccessful) {
            val response = response.body()!!
            print("Response: " + response)
            response.let{
                val phoneEntities = it.map {it.transformToEntity(it)}
                phoneDAO.insertPhones(phoneEntities)
            }
        }
    }

    suspend fun getDetail(id: Int) {
        val response = phoneAPI.getDetail(id)
        if(response.isSuccessful) {
            val response = response.body()!!
            print("Response: " + response)
        }
    }

}

