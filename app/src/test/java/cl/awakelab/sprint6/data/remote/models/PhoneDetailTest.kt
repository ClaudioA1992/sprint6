package cl.awakelab.sprint6.data.remote.models

import org.junit.Assert.*

import org.junit.Test

class PhoneDetailTest {

    @Test
    fun transformToDetailEntity() {
        //Given
        val name = "Galaxy A32"
        val id = 1
        val price = 150000
        val image = ""
        val description = "Cellphone"
        val lastPrice = 200000
        val credit = true
        val cellphoneDetail = PhoneDetail(id, name, price, image, description, lastPrice, credit)
        //When
        val cellphoneDetailEntity = cellphoneDetail.transformToDetailEntity(cellphoneDetail)
        //Then
        assertEquals(cellphoneDetailEntity.id, cellphoneDetail.id)
        assertEquals(cellphoneDetailEntity.name, cellphoneDetail.name)
        assertEquals(cellphoneDetailEntity.price, cellphoneDetail.price)
        assertEquals(cellphoneDetailEntity.image, cellphoneDetail.image)
        assertEquals(cellphoneDetailEntity.description, cellphoneDetail.description)
        assertEquals(cellphoneDetailEntity.lastPrice, cellphoneDetail.lastPrice)
        assertEquals(cellphoneDetailEntity.credit, cellphoneDetail.credit)
    }
}