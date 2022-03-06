package com.test.driverFactories;

import lombok.Getter;

public enum DriverType {
    CHROME("chrome"),
    FIREFOX("firefox");

    @Getter
    private String type;

    DriverType(String type) {
        this.type = type;
    }
}
