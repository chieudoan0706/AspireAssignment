package com.test.constants;

import com.test.utilities.RandomValueGenerator;
import lombok.Getter;

public class ProductConstants {

    public enum TypeConstants {

        CONSUMABLE("Consumable"),
        SERVICE("Service"),
        STORABLE_PRODUCT("Storable Product");

        @Getter
        private String type;

        TypeConstants(String type) {
            this.type = type;
        }

        public static String getRandomProductType() {
            return TypeConstants.values()[RandomValueGenerator.getRandomNumber(0, TypeConstants.values().length - 1)].getType();
        }

    }

    public enum UnitMeasureConstants {
        MM("mm"),
        G("g"),
        CM("cm"),
        INCH_CUBE("in³"),
        INCH("in"),
        OZ("oz"),
        FL_OZ_US("fl oz (US)"),
        SEARCH_MORE("Search More...");

        @Getter
        private String unit;

        UnitMeasureConstants(String unit) {
            this.unit = unit;
        }

        // We don't handle the Search More option for now
        public static String getRandomProductUnitMeasure() {
            return UnitMeasureConstants.values()[RandomValueGenerator.getRandomNumber(0, TypeConstants.values().length - 2)].getUnit();
        }
    }

    public enum CategoryConstants {

        ALL("All"),
        ALL_EXPENSES("All / Expenses"),
        ALL_SALEABLE("All / Saleable");

        @Getter
        private String category;

        CategoryConstants(String category) {
            this.category = category;
        }

        public static String getRandomProductCategory() {
            return CategoryConstants.values()[RandomValueGenerator.getRandomNumber(0, TypeConstants.values().length - 1)].getCategory();
        }
    }

}
