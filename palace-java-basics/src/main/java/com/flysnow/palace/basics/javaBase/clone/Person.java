package com.flysnow.palace.basics.javaBase.clone;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:31
 * @Version V1.0
 */

public class Person implements Cloneable {
    private String name;
    private Integer age;
    private Cat cat;
    // getter和setter省略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}