package com.flysnow.palace.basics.javaDesignMode.SingletonPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.SingletonPattern
 * @Date 2019-12-19 13:27
 * @Author Fly
 * @Description 单例模式：饿汉式
 * @Version 1.0
 */
public class SingleObject {

    //创建 SingleObject 的一个对象
    private static SingleObject instance = new SingleObject();

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject(){}

    //获取唯一可用的对象
    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}