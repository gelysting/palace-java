package cn.knight.fly.test.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 22:23
 * @Copyright © 2018-2999 Fly
 *
 * 修饰静态方法，当synchronized作用与静态方法时，锁是当前类的class对象。
 * 由于静态方法是类成员，因此通过class对象锁可以控制静态成员的并发操作
 *
 * 如果一个线程A调用一个实例对象的非静态同步方法，线程B调用这个实例对象所属的类的静态同步方法，他们之间是不存在互斥的，
 * 因此两个线程占用的锁是不一样的。
 *
 * 但是如果两个方法共同操作同一个静态变量num，那么还是需要考虑线程安全的问题。
 */
public class Test1 implements Runnable{

    public static int num1 = 0;
    public static int num2 = 0;
    public static int num3 = 0;
    public static int num4 = 0;
    public static int num5 = 0;
    public static int num6 = 0;

    public static final Test1 classT1 = new Test1();

    /**
     * 同步非静态方法，锁的是当前实例对象
     */
    public synchronized void test1() {
        num1++;
        //System.out.println("1-Thread-Id:"+Thread.currentThread().getId()+" num1="+num1);
    }

    /**
     * 同步静态方法，锁的是当前类，亦即是类的所有对象
     */
    public static synchronized void test2() {
        num2++;
        //System.out.println("2-Thread-Id:"+Thread.currentThread().getId()+" num2="+num2);
    }

    /**
     * 同步代码块 this（类的实例） 或者 当前局部变量，锁的是当前实例对象
     */
    public void test3(){
        synchronized (this){
            num3 ++;
            //System.out.println("3-Thread-Id:"+Thread.currentThread().getId()+" num3="+num3);
        }
    }

    /**
     * 同步代码块 YourClass.class（类的类型），锁的是当前类，亦即是类的所有对象
     */
    public void test4(){
        synchronized (Test1.class){
            num4 ++;
            //System.out.println("4-Thread-Id:"+Thread.currentThread().getId()+" num4="+num4);
        }
    }

    /**
     * 很特别的用法
     * 同步代码块 (静态)成员变量，锁的是成员对象所指向的实例，全局唯一，安全（一定要用final修饰，不然重新赋值后就不是同一个对象了）
     */
    public void test5(){
        synchronized (classT1){
            num5++;
        }
    }
    /**
     * 很特别的用法
     * 同步代码块 (静态)成员变量的类对象，锁的是成员对象所指向的实例的类对象，全局唯一，安全（一定要用final修饰，不然重新赋值后就不是同一个对象了）
     */
    public void test6(){
        synchronized (classT1.getClass()){
            num6++;
        }
    }

    @Override
    public void run() {
        for(int i = 0; i< 1000000; i++) {
            test1(); // synchronized 修饰类的一般方法，同步锁的是类的实例对象。多线程-多实例-线程不安全；多线程-单实例-线程安全。
            test2(); // synchronized 修饰类的静态方法，同步锁的是类对象。多线程-多实例-线程安全；多线程-单实例-线程安全。
            test3(); // synchronized 修饰代码块this，同步锁的是类的实例对象。多线程-多实例-线程不安全；多线程-单实例-线程安全。
            test4(); // synchronized 修饰代码块Object.class，同步锁的是类对象。多线程-多实例-线程安全；多线程-单实例-线程安全。
            test5(); // synchronized
            test6(); // synchronized
        }
    }

    public static void threadMultiInstance()throws InterruptedException{
        //多线程多实例
        num1 = 0;
        num2 = 0;
        num3 = 0;
        num4 = 0;
        num5 = 0;
        num6 = 0;
        Thread t1 = new Thread(new Test1());
        Thread t2 = new Thread(new Test1());
        Thread t3 = new Thread(classT1);
        Thread t4 = new Thread(classT1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("-----分割线-----");
        System.out.println("多线程-多实例-实例对象锁-不安全-num1="+num1);
        System.out.println("多线程-多实例-类对象锁-安全-num2="+num2);
        System.out.println("多线程-多实例-实例对象锁-不安全-num3="+num3);
        System.out.println("多线程-多实例-类对象锁-安全-num4="+num4);
        System.out.println("多线程-多实例-类的静态实例对象[classT1]锁-num5="+num5);
        System.out.println("多线程-多实例-类的静态实例对象的类对象[classT1.getClass]锁-num6="+num6);
    }
    public static void threadOneInstance()throws InterruptedException{
        //多线程单实例
        num1 = 0;
        num2 = 0;
        num3 = 0;
        num4 = 0;
        num5 = 0;
        num6 = 0;
        Test1 test1 = new Test1();
        Thread ta = new Thread(test1);
        Thread tb = new Thread(test1);
        Thread tc = new Thread(test1);
        Thread td = new Thread(test1);
        ta.start();
        tb.start();
        tc.start();
        td.start();
        ta.join();
        tb.join();
        tc.join();
        td.join();
        System.out.println("-----分割线-----");
        System.out.println("多线程-单实例-实例对象锁-安全-num1="+num1);
        System.out.println("多线程-单实例-类对象锁-安全-num2="+num2);
        System.out.println("多线程-单实例-实例对象锁-安全-num3="+num3);
        System.out.println("多线程-单实例-类对象锁-安全-num4="+num4);
        System.out.println("多线程-单实例-类的静态实例对象[classT1]锁-num5="+num5);
        System.out.println("多线程-单实例-类的静态实例对象的类对象[classT1.getClass]锁-num6="+num6);
    }

    public static void main(String[] args) throws InterruptedException {
        threadMultiInstance();
        threadOneInstance();

        /**
         * 方法：Thread.join(time);
         * 作用：指调用该方法的线程在执行完run()方法后，再执行join方法后面的代码，即将两个线程合并，用于实现同步控制。
         *      主线程将等待该线程的终止，在子线程调用了join(time)方法后，主线程只有等待子线程time时间后才能执行子线程后面的主线程代码。
         */
        /**
         * 使用同步锁的输出结果：
         * -----分割线-----
         * 多线程-多实例-实例对象锁-不安全-num1=1999825
         * 多线程-多实例-类对象锁-安全-num2=2000000
         * 多线程-多实例-实例对象锁-不安全-num3=1999811
         * 多线程-多实例-类对象锁-安全-num4=2000000
         * -----分割线-----
         * 多线程-单实例-实例对象锁-安全-num1=2000000
         * 多线程-单实例-类对象锁-安全-num2=2000000
         * 多线程-单实例-实例对象锁-安全-num3=2000000
         * 多线程-单实例-类对象锁-安全-num4=2000000
         *
         * 将同步锁注释后的输出结果-均不安全
         * -----分割线-----
         * 多线程-多实例-实例对象锁-不安全-num1=1476539
         * 多线程-多实例-类对象锁-安全-num2=1473347
         * 多线程-多实例-实例对象锁-不安全-num3=1492868
         * 多线程-多实例-类对象锁-安全-num4=1502743
         * -----分割线-----
         * 多线程-单实例-实例对象锁-安全-num1=1204339
         * 多线程-单实例-类对象锁-安全-num2=1196641
         * 多线程-单实例-实例对象锁-安全-num3=1203498
         * 多线程-单实例-类对象锁-安全-num4=1213547
         */
    }
}
