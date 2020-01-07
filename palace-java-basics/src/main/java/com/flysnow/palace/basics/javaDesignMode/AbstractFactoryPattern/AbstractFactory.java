package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern
 * @Date 2019-12-19 13:14
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
