
> synchronized与volatile的总结

1.synchronized关键字一次只允许一个线程持有某个特定的锁，因此可以使用该特性实现对共享数据的访问，一次只有一个线程能够使用该共享数据。

2.volatile主要是使变量的值在发生改变时能尽快的让其他的线程知道，使线程获取到数据是最新的。

3.volatile本质是告诉jvm当前变量在寄存器中的值是不确定的，需要从主存中读取，synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他的线程被阻塞住。

4.volatile仅能够用在变量级别，synchronized则可以使用在变量和方法中。

5.volatile仅能够实现变量修改的可见性，而synchronized可以保证变量修改的可见性和原子性。

6.volatile不会造成线程的阻塞，而synchronized可能会造成线程的阻塞。

> 
