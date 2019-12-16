package cn.knight.fly.test.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 22:43
 * @Copyright © 2018-2999 Fly
 *
 * volatile是一个类型修饰符，他主要用于修饰被不同的线程访问和修改的变量，他是作为指令关键字，确保本条指令不会因编译器的优化而省略，
 * 且要求每次直接读值。
 *
 * 可以确保将变量的更新操作通知到其他的线程，当把变量声明为volatile类型，编译与运行时都会注意到这个变量是共享的，因此该变量不会被缓存在寄存器或者对其他处理器不可见的地方，因此在读取volatile类型的变量时总会返回最新写入的值。
 *
 * 在访问volatile变量时不会执行加锁操作，因此也就不会执行线程阻塞，他是一种比synchronized关键字更轻量级的同步机制。
 *
 * 此时，当两个线程同时操作volatile修饰的变量flag时，两个线程每次拿到的都是flag的最新的值，
 * 但是这个时候对于获取到flag的值两个线程并没有一个互斥的操作，并不能保证操作的原子性。
 */
public class volatileTest02 {

    public static void main(String[] args) {

        volatileTest01 test = new volatileTest01();

        Thread t1 = new Thread(test, "test1");
        Thread t2 = new Thread(test, "test2");

        t1.start();
        t2.start();

    }


}
