package com.flysnow.palace.basics.javaDesignMode.ProxyPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.ProxyPattern
 * @Date 2019-12-19 12:59
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("fe.jpg");
        System.out.println("xxxx");
        // 图像将从磁盘加载
        image.display();
        System.out.println("xxxx");
        // 图像不需要从磁盘加载
        image.display();

        /**
         * 输出
         * xxxx
         * Loading fe.jpg
         * Displaying fe.jpg
         * xxxx
         * Displaying fe.jpg
         */
    }
}