package com.flysnow.palace.basics.javaBase.clone;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:30
 * @Version V1.0
 */

public class Cat implements Cloneable {
    private String name;
    // getter和setter省略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}