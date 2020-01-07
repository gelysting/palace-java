package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.FactoryPattern
 * @Date 2019-12-19 13:08
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}