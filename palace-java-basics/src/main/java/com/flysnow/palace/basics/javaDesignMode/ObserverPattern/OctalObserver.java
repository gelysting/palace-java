package com.flysnow.palace.basics.javaDesignMode.ObserverPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ObserverPattern
 * @Date 2019-12-19 12:32
 * @Author Fly
 * @Description 拓展继承抽象类的观察者
 * @Version 1.0
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) );
    }
}