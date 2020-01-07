package com.flysnow.palace.basics.javaDesignMode.ObserverPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ObserverPattern
 * @Date 2019-12-19 12:31
 * @Author Fly
 * @Description 抽象类 绑定被观察对象的观察者
 * @Version 1.0
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}