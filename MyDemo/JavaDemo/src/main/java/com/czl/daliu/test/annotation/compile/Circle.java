package com.czl.daliu.test.annotation.compile;

/**
 * 圆形
 */
@Factory(id = "Circle", type = IShape.class)
public class Circle implements IShape {
    @Override
    public void draw() {
        System.out.println("Draw a circle");
    }
}
