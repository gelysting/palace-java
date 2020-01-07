package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern
 * @Date 2019-12-19 13:13
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}