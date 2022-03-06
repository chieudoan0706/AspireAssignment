package com.test.utilities;

import com.github.javafaker.Faker;

/**
 * The type Random value generator.
 */
public class RandomValueGenerator {

    /**
     * The constant faker.
     */
    public static Faker faker = Faker.instance();

    /**
     * Gets product name.
     *
     * @return the product name
     */
    public static String getProductName() {
        return faker.commerce().productName();
    }

    /**
     * Gets random number.
     *
     * @param low  the low
     * @param high the high
     * @return the random number
     */
    public static int getRandomNumber(int low, int high) {
        return faker.number().numberBetween(low, high);
    }

    /**
     * Gets product internal reference.
     *
     * @return the product internal reference
     */
    public static String getProductInternalReference() {
        return faker.commerce().department() + getRandomPrice(10,10000);
    }

    /**
     * Gets product bar code.
     *
     * @return the product bar code
     */
    public static String getProductBarCode() {
        return faker.commerce().promotionCode();
    }

    /**
     * Gets random price.
     *
     * @param low  the low
     * @param high the high
     * @return the random price
     */
    public static String getRandomPrice(double low, double high) {
        return faker.commerce().price(low, high);
    }

}
