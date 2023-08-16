package cl.awakelab.sprint6.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "phone")
class PhoneEntity(@PrimaryKey val id: Int, val name: String, val price: Int, val image: String)