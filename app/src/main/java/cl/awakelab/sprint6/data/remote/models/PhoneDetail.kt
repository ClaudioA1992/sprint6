package cl.awakelab.sprint6.data.remote.models

import cl.awakelab.sprint6.data.local.entities.PhoneDetailEntity

class PhoneDetail(val id: Int, val name: String, val price: Int, val image: String,
                  val description: String, val lastPrice: Int, val credit: Boolean) {
    fun transformToDetailEntity(it: PhoneDetail): PhoneDetailEntity {
        return PhoneDetailEntity(it.id, it.name, it.price, it.image, it.description, it.lastPrice, it.credit)
    }
}