package com.flysnow.palace.basics.javaDesignMode.BuilderPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.BuilderPattern
 * @Date 2019-12-19 13:36
 * @Author Fly
 * @Description Burger 汉堡
 * @Version 1.0
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}