package com.yang.sse.demo.entity;

/**
 * Description:
 *
 * @author mark
 * Date 2020/6/12
 */
public final class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
