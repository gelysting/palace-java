package com.flysnow.palace.basics.javaDesignMode.BuilderPattern;

/**
 * @Package com.flysnow.palace.basics.javaDesignMode.BuilderPattern
 * @Date 2019-12-19 13:41
 * @Author Fly
 * @Description
 * @Version 1.0
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());

        /**
         * 输出
         * Veg Meal
         * Item : Veg Burger, Packing : Wrapper, Price : 25.0
         * Item : Coke, Packing : Bottle, Price : 30.0
         * Total Cost: 55.0
         *
         *
         * Non-Veg Meal
         * Item : Chicken Burger, Packing : Wrapper, Price : 50.5
         * Item : Pepsi, Packing : Bottle, Price : 35.0
         * Total Cost: 85.5
         */
    }
}
