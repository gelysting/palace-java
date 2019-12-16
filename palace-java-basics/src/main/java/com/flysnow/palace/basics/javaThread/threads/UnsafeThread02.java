package cn.knight.fly.test.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 21:50
 * @Copyright © 2018-2999 Fly
 *
 * 修饰实例方法
 * 在该方法中 synchronized 修饰的是实例方法 task，此时线程的锁便是实例对象 instance。
 * 多个线程操作同一个对象，可以实现锁
 * 多个线程操作不同的对象，各自获得不同的锁，则此时存在线程安全问题
 */
public class UnsafeThread02 implements Runnable {

    public static int num=0;

    public synchronized void task(){
        num ++;
        System.err.println("Thread-ID："+Thread.currentThread().getId()+" num="+num);
    }

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            task();
        }
    }

    public static void main(String[] args) throws Exception{

        UnsafeThread02 unsafeThread02 = new UnsafeThread02();

        Thread t1 = new Thread(unsafeThread02);
        Thread t2 = new Thread(unsafeThread02);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //先调用join方法，则先运行线程方法，然后运行到此处；否则先运行下面的语句，再运行线程方法
        System.out.println("num="+num);
    }
}
