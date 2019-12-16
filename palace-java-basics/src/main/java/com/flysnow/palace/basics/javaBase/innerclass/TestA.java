package cn.knight.fly.test.innerclass;

/**
 * @Package cn.knight.fly.test.innerclass
 * @Description 内部类有一个特征：内部类当中可以调用外部类当中的属性和方法，而外部类却不能调用内部类当中的。
 * @Author Fly
 * @Date 2019-11-05 10:09
 * @Version V1.0
 */
public class TestA {

    int a = 10;
    public TestA(){
        System.out.println("Create a Class TestA.");
    }
    public void sayA(){
        System.out.println("I am a Method In The Class TestA. And call attribute a=" + a) ;

        /**
         * 外部类不能直接调用内部类的属性以及方法。
         */
        //System.out.println("call Inner Class TestB Attribute：" + b );

        /**
         * 外部类可以按常规对象处理方法，进行实例化内部类从而调用内部类的方法与属性
         */
        //TestB bCalss = new TestB();
        //System.out.println("call Inner Class TestB Attribute：" + bCalss.b );
        //bCalss.sayB();
    }

    class TestB{
        int b = 20;
        public TestB(){
            System.out.println("Create a Inner Class TestB In The Class TestA.");
        }
        public void sayB(){
            System.out.println("I am a Method In The Class TestB. And call attribute b=" + b) ;
            System.out.println("call Class TestA Attribute a=" + a );
        }
    }

}

