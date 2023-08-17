package cl.awakelab.sprint6.data.remote.models

import cl.awakelab.sprint6.data.local.entities.PhoneEntity

class Phone(val id: Int, val name: String, val price: Int, val image: String) {
    fun transformToEntity(phone: Phone): PhoneEntity {
        return PhoneEntity(id, name, price, image)
    }
}