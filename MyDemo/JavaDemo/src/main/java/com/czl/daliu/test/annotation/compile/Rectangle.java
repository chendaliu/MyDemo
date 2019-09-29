package com.czl.daliu.test.annotation.compile;

/**
 * 长方形
 */
@Factory(id = "Rectangle", type = IShape.class)
public class Rectangle implements IShape {
    @Override
    public void draw() {
        System.out.println("Draw a Rectangle");
    }
}
