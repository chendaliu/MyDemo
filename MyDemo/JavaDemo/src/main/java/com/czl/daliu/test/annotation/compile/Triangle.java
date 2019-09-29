package com.czl.daliu.test.annotation.compile;

/**
 * 三角形
 */
@Factory(id = "Triangle", type = IShape.class)
public class Triangle implements IShape {
    @Override
    public void draw() {
        System.out.println("Draw a Triangle");
    }
}
