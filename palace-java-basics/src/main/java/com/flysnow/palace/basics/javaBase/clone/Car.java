package cn.knight.fly.test.clone;

import java.io.Serializable;

/**
 * @Package cn.knight.fly.test.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:47
 * @Version V1.0
 */
public class Car implements Serializable {
    private static final long serialVersionUID = -7633040136520448512L;
    private String name;
    private String color;

    public Car() {}

    public Car(String name, String color) {
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car [name=" + name + ", color=" + color + "]";
    }
}