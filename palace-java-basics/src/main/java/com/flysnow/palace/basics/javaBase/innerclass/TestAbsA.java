package cn.knight.fly.test.innerclass;

/**
 * @Package cn.knight.fly.test.innerclass
 * @Description
 * @Author Fly
 * @Date 2019-11-05 11:41
 * @Version V1.0
 */
abstract class TestAbsA {
    int aa = 11; //抽象类的属性是不会被调用的，除了方法
    public TestAbsA (){
        System.out.println("Create a abstract class TestAbsA.");
    }

    /**
     * 抽象类当中是允许有具体方法来进行实现的，而接口不可以有具体的方法实现。
     */
    public void sayAA(){
        System.out.println("I am a Method In The abstract Class TestAbsA. And call attribute aa=" + aa);
    }
}
