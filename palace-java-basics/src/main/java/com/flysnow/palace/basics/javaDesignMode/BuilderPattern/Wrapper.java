package com.flysnow.palace.basics.javaDesignMode.BuilderPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.BuilderPattern
 * @Date 2019-12-19 13:34
 * @Author Fly
 * @Description Wrapper 包装纸包装
 * @Version 1.0
 */
public class Wrapper implements Packing {

    @Override
    public String pack() {
        return "Wrapper";
    }
}