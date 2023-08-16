package cl.awakelab.sprint6.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.awakelab.sprint6.data.local.entities.PhoneDetailEntity
import cl.awakelab.sprint6.data.local.entities.PhoneEntity

@Database(entities = [PhoneEntity::class, PhoneDetailEntity::class], version = 1)
abstract class PhoneDatabase: RoomDatabase() {

    abstract fun getPhoneDao(): PhoneDao

    companion object {
        @Volatile
        private var INSTANCE: PhoneDatabase? = null

        fun getDatabase(context: Context): PhoneDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDatabase::class.java,
                    "store_database"
                ).build()

                INSTANCE = instance
                return instance

            }
        }
    }

}

