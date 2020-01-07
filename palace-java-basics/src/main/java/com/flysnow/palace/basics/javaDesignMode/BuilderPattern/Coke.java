package com.flysnow.palace.basics.javaDesignMode.BuilderPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.BuilderPattern
 * @Date 2019-12-19 13:39
 * @Author Fly
 * @Description Coke 可口可乐
 * @Version 1.0
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
