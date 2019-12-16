package com.flysnow.palace.basics.javaThread.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 22:55
 * @Copyright © 2018-2999 Fly
 *
 *
 * volatile的可见性：当多个线程访问同一个变量时，一个线程修改了这个变量，其他得线程能够立即看的到值得改变，这一个特性对于volatile是满足的。
 *
 * volatile的原子性：不满足。
 *
 * 对于下面的例子，正常期望的结果应该是每次运行都能得到值为10000，但是在实际的运行结果中发现每次得到的结果都不一样，
 * 因此volatile虽然保证了可见性，确保每次拿到的值都是最新的，但是不能确保操作的原子性。
 *
 */
public class volatile03 {

        public volatile int inc = 0;

        public void increase() {
            inc++;
        }

        public static void main(String[] args) {

            final volatile03 test = new volatile03();

            for(int i=0; i<5; i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<10;j++)
                            test.increase();
                    };
                }.start();
            }

            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }

}
