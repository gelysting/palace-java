package com.flysnow.palace.basics.javaDesignMode.BuilderPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.BuilderPattern
 * @Date 2019-12-19 13:33
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}