package com.flysnow.palace.basics.javaBase.clone;

import java.io.Serializable;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:50
 * @Version V1.0
 */
public class PersonB implements Serializable {
    private static final long serialVersionUID = 4792126594710124401L;
    private String name;
    private int age;
    private Car car;

    public PersonB() {}

    public PersonB(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonB(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    // getter和setter方法省略

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
    }
}
