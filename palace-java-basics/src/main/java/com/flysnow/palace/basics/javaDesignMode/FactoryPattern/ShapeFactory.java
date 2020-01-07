package com.flysnow.palace.basics.javaDesignMode.FactoryPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.FactoryPattern
 * @Date 2019-12-19 13:09
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}