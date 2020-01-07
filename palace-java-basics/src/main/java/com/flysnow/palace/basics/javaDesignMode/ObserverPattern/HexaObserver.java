package com.flysnow.palace.basics.javaDesignMode.ObserverPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ObserverPattern
 * @Date 2019-12-19 12:32
 * @Author Fly
 * @Description 拓展继承抽象类的观察者
 * @Version 1.0
 */
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}