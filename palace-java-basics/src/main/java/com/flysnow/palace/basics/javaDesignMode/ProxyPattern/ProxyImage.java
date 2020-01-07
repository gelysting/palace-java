package com.flysnow.palace.basics.javaDesignMode.ProxyPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ProxyPattern
 * @Date 2019-12-19 12:59
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}