package cn.knight.fly.test.threads;

/**
 * @author Fly
 * @version V1.0
 * @Package cn.knight.fly
 * @date 2019-10-29 17:43
 * @Copyright © 2018-2999 Fly
 */
public class UnsafeThread01 implements Runnable {
    //设置标识符号
    public boolean flag =true;
    //票的数量
    public int num=10;

    @Override
    public void run() {
        // 当还有票 线程就一直执行byTicket()买票操作
        while (flag){
            try {
                byTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 添加锁
     * @throws InterruptedException
     */
    public  synchronized void byTicket() throws InterruptedException {
            if (num <= 0) {
                flag = false;
                return;
            }
        /* 模拟网路延迟 */
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName()+"--->"+num--);
    }

    public static void main(String[] args) throws Exception{

        UnsafeThread01 thread01 = new UnsafeThread01();
        // 开启三个线程 模拟多个人同时买票
        Thread t1 = new Thread(thread01,"黄牛1");
        Thread t2 = new Thread(thread01,"黄牛2");
        Thread t3 = new Thread(thread01,"黄牛3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

    }
}
