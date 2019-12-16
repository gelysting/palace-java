package com.flysnow.palace.basics.javaThread.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 22:40
 * @Copyright © 2018-2999 Fly
 */
class volatileTest01 implements Runnable {

    private volatile boolean flag = false;

    public void run() {

        for (int i = 0; i < 5; i++) {
            try {

                System.out.println("Thread-name：" + Thread.currentThread().getName() + " i=" + i + " flag=" + flag);

                //线程名为test1，则将flag值改为true，同时打印出新旧flag值
                if ("test1".equals(Thread.currentThread().getName())) {
                    boolean oldflag = flag;
                    flag = true;
                    System.out.println("test1 OldFlag=" + oldflag + " NewFlag=" + flag);
                }
                else if ("test2".equals(Thread.currentThread().getName())) {
                    //如果线程名为test2，则将flag值修改为false，同时打印出新旧flag值
                    boolean oldflag = flag;
                    flag = false;
                    System.out.println("test2 OldFlag=" + oldflag + " NewFlag=" + flag);
                }
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

