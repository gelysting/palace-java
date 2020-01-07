package com.flysnow.palace.basics.javaDesignMode.ObserverPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ObserverPattern
 * @Date 2019-12-19 12:32
 * @Author Fly
 * @Description 拓展继承抽象类的观察者
 * @Version 1.0
 */
public class BinaryObserver extends Observer{

    //绑定被观察者的构造方法
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this); //使用被观察者的方法 对 观察者与被观察者 进行连接
    }

    @Override
    public void update() {
        System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) );
    }
}