package com.flysnow.palace.basics.javaBase.polymc;

/**
 * @Package com.flysnow.palace.basics.javaBase.polymc
 * @Description
 * @Author Fly
 * @Date 2019-11-05 16:38
 * @Version V1.0
 */
public class TestB extends TestA{

    String show(TestB obj) {
        return ("B-B");
    }

    String show(TestA obj) {
        return ("B-A");
    }

}
