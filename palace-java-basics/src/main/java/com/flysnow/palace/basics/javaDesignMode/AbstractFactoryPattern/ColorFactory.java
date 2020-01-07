package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.AbstractFactoryPattern
 * @Date 2019-12-19 13:20
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }
}