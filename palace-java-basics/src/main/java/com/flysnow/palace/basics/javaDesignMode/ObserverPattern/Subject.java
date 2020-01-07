package com.flysnow.palace.basics.javaDesignMode.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ObserverPattern
 * @Date 2019-12-19 12:31
 * @Author Fly
 * @Description 绑定有观察者的被观察对象
 * @Version 1.0
 */
public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    //连接到观察者
    public void attach(Observer observer){
        observers.add(observer);
    }

    //通知观察者
    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}