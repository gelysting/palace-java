package com.flysnow.palace.basics.javaDesignMode.ObserverPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ObserverPattern
 * @Date 2019-12-19 12:36
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject); //使用绑定被观察者的构造方法实例化观察者
        new OctalObserver(subject); //使用绑定被观察者的构造方法实例化观察者
        new BinaryObserver(subject); //使用绑定被观察者的构造方法实例化观察者

        System.out.println("First state change: 15");
        subject.setState(15);

        System.out.println("Second state change: 10");
        subject.setState(10);

        /**
         * 输出
         * First state change: 15
         * Hex String: F
         * Octal String: 17
         * Binary String: 1111
         * Second state change: 10
         * Hex String: A
         * Octal String: 12
         * Binary String: 1010
         */
    }
}