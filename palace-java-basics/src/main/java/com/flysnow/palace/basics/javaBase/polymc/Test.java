package com.flysnow.palace.basics.javaBase.polymc;

/**
 * @Package com.flysnow.palace.basics.javaBase.polymc
 * @Description
 * @Author Fly
 * @Date 2019-11-05 16:44
 * @Version V1.0
 */
public class Test {

    public static void main(String[] args){

        TestA a1 = new TestA();
        TestA a2 = new TestB();
        TestB b = new TestB();
        TestC c = new TestC();
        TestD d = new TestD();

        // C/D —> B —> A , C/D 继承 B 继承 A
        System.out.println("1--" + a1.show(b)); // a1 本身为TestA对象的引用 , b 向上转型为 TestA
        System.out.println("2--" + a1.show(c)); // a1 本身为TestA对象的引用 , c 向上转型为 TestA
        System.out.println("3--" + a1.show(d)); // a1 本身为TestA对象的引用 , d 直接调用 TestA 的重载方法

        /**
         * 继承链中对象方法的调用的优先级：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
         *
         * a2 解析： 类型是TestA的引用类型，它指向类型为TestB的对象，向上转型，丢失了不在TestA中的方法TestB.show(TestB obj)。因此，TestA 确定可调用的方法：show(TestD obj)和show(TestA obj)。
         * 同时，因为TestB 继承 TestA 时，重写了 show(TestA obj) 方法，
         * 因此 a2这个指向TestB的引用对象 实际可调用的方法为：本身引用类型的方法TestA.show(TestD obj) 和 向上转型重写的方法TestB.show(TestA obj)
         *
         */
        System.out.println("4--" + a2.show(b)); // a2 为TestB对象的向上转型的引用 , 所以 a2 有TestB的重写TestA的方法 同时包含在TestA的方法而不在TestB中重写的方法（继承的特性）----难点入参b 隐式向上转型到a2的父类即TestA
        System.out.println("5--" + a2.show(c)); // a2 为TestB对象的向上转型的引用 , 所以 a2 有TestB的重写TestA的方法 同时包含在TestA的方法而不在TestB中重写的方法（继承的特性）----难点入参c 隐式向上转型到a2的父类即TestA
        System.out.println("6--" + a2.show(d)); // a2 为TestB对象的向上转型的引用 , 所以 a2 有TestB的重写TestA的方法 同时包含在TestA的方法而不在TestB中重写的方法（继承的特性）


        System.out.println("7--" + b.show(b)); // b , 入参解析为本身
        System.out.println("8--" + b.show(c)); // c->b , 入参解析为本身
        System.out.println("9--" + b.show(d)); // b 继承 TestA 参数有TestD , 方法重载 , 调用了父类的方法

        //输出结果为：
        //1--A-A
        //2--A-A
        //3--A-D
        //4--B-A
        //5--B-A
        //6--A-D
        //7--B-B
        //8--B-B
        //9--A-D
    }
}
