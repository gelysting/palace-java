package com.flysnow.palace.basics.javaDesignMode.SingletonPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.SingletonPattern
 * @Date 2019-12-19 13:28
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {

        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();

        //显示消息
        object.showMessage();


        /**
         * 输出
         * Hello World!
         */
    }
}
