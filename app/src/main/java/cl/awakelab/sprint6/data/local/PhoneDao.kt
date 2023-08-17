package cl.awakelab.sprint6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.awakelab.sprint6.data.local.entities.PhoneDetailEntity
import cl.awakelab.sprint6.data.local.entities.PhoneEntity

@Dao
interface PhoneDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone: PhoneEntity)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertPhones(phones: List<PhoneEntity>)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phoneDetail: PhoneDetailEntity)

    @Query("select * from phone order by name asc")
    fun selectPhones(): LiveData<List<PhoneEntity>>

    @Query("select * from phone_detail where id = :id order by name asc")
    fun selectPhonesDetail(id: Int): LiveData<PhoneDetailEntity>

}

