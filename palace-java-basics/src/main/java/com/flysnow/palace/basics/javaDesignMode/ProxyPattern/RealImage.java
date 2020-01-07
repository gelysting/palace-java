package com.flysnow.palace.basics.javaDesignMode.ProxyPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ProxyPattern
 * @Date 2019-12-19 12:58
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}