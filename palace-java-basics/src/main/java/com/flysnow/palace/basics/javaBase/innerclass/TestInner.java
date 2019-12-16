package cn.knight.fly.test.innerclass;

/**
 * @Package cn.knight.fly.test.innerclass
 * @Description
 * @Author Fly
 * @Date 2019-11-05 10:10
 * @Version V1.0
 */
public class TestInner {

    public static void main(String[] args){

        System.out.println("以下开始测试常规外部类");
        TestA classA = new TestA();
        classA.sayA();

        System.out.println("\n以下开始测试内部类");
        TestA.TestB classB =  new TestA().new TestB();  //这里开始创建内部类的对象，这是创建内部类对象的专用格式，相当于在创建了一个外部类对象的基础上再创建一个内部类对象
        classB.sayB();
        System.out.println( "调用内部类的属性,b=" + classB.b );


        /**
         * 匿名内部类可以继承两类数据结构：
         * 一：抽象类
         * 二：接口
         */
        //--抽象类
        System.out.println("\n以下开始测试匿名内部类-抽象类");
        TestAbsA classAbsA = new TestAbsA() {
            int aa = 22;
            int bb = 33;
            @Override  //抽象类的方法可以直接继承，不是必须要重写的
            public void sayAA() {
                System.out.println("这是匿名内部类当中的方法，重写了抽象类的方法，bb+aa="+ (bb+aa)); //这里的aa，是本匿名类的aa而非抽象类的aa
                super.sayAA();
            }
        };//在使用匿名内部类的时候，当这个类在陈述完之后，是需要打分号的。

        classAbsA.sayAA();
        //System.out.println( "调用匿名内部类的属性，bb=" + classAbsA.bb ); //错误的写法,外部类不能直接调用内部类的属性以及方法。Cannot resolve symbol 'bb'
        System.out.println( "调用匿名内部类的属性，aa=" + classAbsA.aa ); //这里的aa是抽象类的aa。即是父类当中的属性，和我们多态的性质相重


        //--接口类
        System.out.println("\n以下开始测试匿名内部类-接口类");
        //这就是所谓的向上转型。现在我们再来试试接口的匿名内部类实现
        TestInterfaceA classInterA = new TestInterfaceA() {
            int bb = 22;
            @Override //由于必须实现接口当中的方法（Java的接口类 类似于 C++的虚基类，虚类的函数必须实现，接口的方法也是必须实现），因此idea就自动为我们写上了override的标识符了
            public void sayIA() {
                System.out.println("这是继承接口当中的方法，调用接口属性，aa=" + aa);
                System.out.println("这是继承接口当中的方法，调用本实现匿名内部类的属性，bb=" + bb);
            }
        };
        classInterA.sayIA();
    }
}
