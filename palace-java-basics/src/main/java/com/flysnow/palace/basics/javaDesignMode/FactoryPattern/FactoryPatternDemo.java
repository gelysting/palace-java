package com.flysnow.palace.basics.javaDesignMode.FactoryPattern;


/**
 * @Package com.flysnow.palace.basics.javaDesignMode
 * @Date 2019-12-19 13:05
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.draw();

        /**
         * 输出
         * Inside Circle::draw() method.
         * Inside Rectangle::draw() method.
         * Inside Square::draw() method.
         */
    }
}