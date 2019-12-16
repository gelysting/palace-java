package com.flysnow.palace.basics.javaThread.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 22:36
 * @Copyright © 2018-2999 Fly
 *
 * 将synchronized作用于一个给定的实例对象instance，当前实例对象就是锁对象，
 * 每次线程进入synchronized的代码块时就要求当前线程必须拿到instance实例的对象锁，其他的线程就必须等待。
 *
 */
public class Test2 implements Runnable{

    public static int num = 0;

    static Test2 instance = new Test2();

    @Override
    public void run() {
        synchronized(instance) {
            for(int i = 0; i< 10; i++) {
                num++;
                System.out.println("Thread-Id:"+Thread.currentThread().getId()+" num="+num);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num="+num);   /**num=10*/

    }
}