package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class ShoppingCartTest {

    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setUp() {
        cart = ShoppingCart()
    }

//    @Test
//    fun `Add multiple products, total price sum is correct`() {
//        // GIVEN
//        val product = Product(
//            id = 0,
//            name = "Ice cream",
//            price = 5.0
//        )
//        cart.addProduct(product, 4)
//
//        // ACTION
//        val priceSum = cart.getTotalCost()
//
//        // ASSERTION
//        assertThat(priceSum).isEqualTo(20.0)
//    }

//    @ParameterizedTest
//    @ValueSource(ints = [1,2,3,4,5])
//    fun `Add multiple products, total price sum is correct`(quantity: Int) {
//        // GIVEN
//        val product = Product(
//            id = 0,
//            name = "Ice cream",
//            price = 5.0
//        )
//        cart.addProduct(product, quantity)
//
//        // ACTION
//        val priceSum = cart.getTotalCost()
//
//        // ASSERTION
//        assertThat(priceSum).isEqualTo(quantity * 5.0)
//    }

    @ParameterizedTest
    @CsvSource(
        "3, 15.0",
        "0, 0.0"
    )
    fun `Add multiple products, total price sum is correct`(quantity: Int, expectedSum: Double) {
        // GIVEN
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 5.0
        )
        cart.addProduct(product, quantity)

        // ACTION
        val priceSum = cart.getTotalCost()

        // ASSERTION
        assertThat(priceSum).isEqualTo(quantity * 5.0)
    }


    @RepeatedTest(5)
    fun `Add product with negative quantity, throws Exception`() {
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 5.0
        )

        assertFailure {
            cart.addProduct(product, -5)
        }
    }

    /*
    Way to test private function
     */
    @Test
    fun `isValidProduct returns invalid for not existing product`() {
        val product = Product(
            id = 1234,
            name = "Ice cream",
            price = 5.0
        )
        cart.addProduct(product, 4)

        val totalPriceSum = cart.getTotalCost()

        assertThat(totalPriceSum).isEqualTo(0.0)
    }
}