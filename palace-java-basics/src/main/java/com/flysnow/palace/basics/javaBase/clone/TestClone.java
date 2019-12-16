package com.flysnow.palace.basics.javaBase.clone;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:28
 * @Version V1.0
 */
public class TestClone {

    /**
     * 浅克隆和深克隆区别
     * @throws CloneNotSupportedException
     */
    public static void StudentTest () throws CloneNotSupportedException {
        Student stu1 = new Student();
        stu1.setNumber(12345);

        // stu1克隆出stu2
        Student stu2 = (Student) stu1.clone();

        System.out.println("学生1:" + stu1.getNumber()); // 12345
        System.out.println("学生2:" + stu2.getNumber()); // 12345

        stu2.setNumber(54321); // 修改stu2

        System.out.println("学生1:" + stu1.getNumber()); // 12345
        System.out.println("学生2:" + stu2.getNumber()); // 54321
    }

    /**
     * 浅克隆
     * @throws CloneNotSupportedException
     */
    public static void PersonTest () throws CloneNotSupportedException {
        Cat cat = new Cat();
        cat.setName("狸花");

        Person per1 = new Person();
        per1.setName("张三");
        per1.setAge(10);
        per1.setCat(cat);

        // per1克隆出per2
        Person per2 = (Person) per1.clone();

        System.out.println(per1.getName() + "-" + per1.getAge() + "-" + per1.getCat().getName()); // 张三-10-狸花
        System.out.println(per2.getName() + "-" + per2.getAge() + "-" + per2.getCat().getName()); // 张三-10-狸花
        System.out.println(per1.getName() == per2.getName()); // true
        System.out.println(per1.getAge() == per2.getAge()); // true
        System.out.println(per1.getCat() == per1.getCat()); // true

        // 修改per1
        per1.setName("李四"); // per1的name属性保存另一个"字符串"的地址
        per1.setAge(20);
        cat.setName("小橘");

        System.out.println(per1.getName() + "-" + per1.getAge() + "-" + per1.getCat().getName()); // 李四-20-小橘
        System.out.println(per2.getName() + "-" + per2.getAge() + "-" + per2.getCat().getName()); // 张三-10-小橘
        System.out.println(per1.getName() == per2.getName()); // false
        System.out.println(per1.getAge() == per2.getAge()); // false
        System.out.println(per1.getCat() == per1.getCat()); // true
    }

    /**
     * 理论上String和Integer类型的属性的克隆也是浅克隆。
     * 但是，String和Integer是不可变的。所以，实际上当String和Integer类型的属性改变时，克隆对象不会跟着改变。
     * @throws CloneNotSupportedException
     */
    public static void PersonTest2() throws CloneNotSupportedException {
        Cat cat = new Cat();
        cat.setName("狸花");

        Person per1 = new Person();
        String name = new String("张三");
        per1.setName(name);
        Integer age = new Integer("10");
        per1.setAge(age);
        per1.setCat(cat);

        // per1克隆出per2
        Person per2 = (Person) per1.clone();

        System.out.println(per1.getName() + "-" + per1.getAge() + "-" + per1.getCat().getName()); // 张三-10-狸花
        System.out.println(per2.getName() + "-" + per2.getAge() + "-" + per2.getCat().getName()); // 张三-10-狸花
        System.out.println(per1.getName() == per2.getName()); // true 说明是浅克隆
        System.out.println(per1.getAge() == per2.getAge()); // true 说明是浅克隆
        System.out.println(per1.getCat() == per1.getCat()); // true 说明是浅克隆

        // 修改per1
        name = new String("李四"); // 只是修改了name变量里面保存的地址值
        age = new Integer("20"); // 只是修改了age变量里面保存的地址值
        cat.setName("小橘");

        System.out.println(per1.getName() + "-" + per1.getAge() + "-" + per1.getCat().getName()); // 张三-10-小橘
        System.out.println(per2.getName() + "-" + per2.getAge() + "-" + per2.getCat().getName()); // 张三-10-小橘
        System.out.println(per1.getName() == per2.getName()); // true
        System.out.println(per1.getAge() == per2.getAge()); // true
        System.out.println(per1.getCat() == per1.getCat()); // true
    }

    /**
     * 深克隆
     * @throws CloneNotSupportedException
     */
    public static void deepCloneTest() throws CloneNotSupportedException {
        Teacher teacher = new Teacher();
        teacher.setAge(40);
        teacher.setName("teacher zhang");

        StudentB stu1 = new StudentB();
        stu1.setAge(10);
        stu1.setName("张三");
        stu1.setTeacher(teacher);

        //stu1深克隆出stu2
        StudentB stu2 = (StudentB) stu1.clone();

        // 这里是深复制，所以这时候Student中的teacher就是teacher这个对象的一个复制，就和student2是Student的一个复制
        // 所以下面teacher.setName只是对他原来的这个对象更改，但是复制的那个并没有更改
        System.out.println(stu1.getName() + "-" + stu1.getAge() + "-" + stu1.getTeacher().getName());//张三-10-teacher zhang
        System.out.println(stu2.getName() + "-" + stu2.getAge() + "-" + stu2.getTeacher().getName());//张三-10-teacher zhang

        // 修改stu1
        stu1.setName("李四");
        stu1.setAge(20);
        teacher.setName("teacher wang");

        System.out.println(stu1.getName() + "-" + stu1.getAge() + "-" + stu1.getTeacher().getName());//李四-20-teacher wang
        System.out.println(stu2.getName() + "-" + stu2.getAge() + "-" + stu2.getTeacher().getName());//张三-10-teacher zhang
    }


    /**
     * 序列化和反序列化实现深克隆
     */
    public static void SerialTest() {
        Car car = new Car("BYD", "black");
        PersonB p1 = new PersonB("张三", 20, car);
        PersonB p2 = cloneSerialUtil.cloneObject(p1);
        PersonB p3 = CloneUtil.cloneObject(p1);

        System.out.println("P1:" + p1);//P1:Person [name=张三, age=20, car=Car [name=BYD, color=black]]
        System.out.println("p2:" + p2);//p2:Person [name=张三, age=20, car=Car [name=BYD, color=black]]
        System.out.println("p3:" + p3);//p2:Person [name=张三, age=20, car=Car [name=BYD, color=black]]

        if (p2 != null) {
            car.setName("宝马");
            car.setColor("red");
        }
        if (p3 != null) {
            car.setName("奔驰");
            car.setColor("blue");
        }

        System.out.println("P1:" + p1);//P1:Person [name=张三, age=20, car=Car [name=宝马, color=red]]
        System.out.println("p2:" + p2);//p2:Person [name=张三, age=20, car=Car [name=BYD, color=black]]
        System.out.println("p3:" + p3);//p2:Person [name=张三, age=20, car=Car [name=BYD, color=black]]

    }

    /**
     * 主线程入口
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception{

        StudentTest();
        PersonTest();
        PersonTest2();
        deepCloneTest();
        SerialTest();

    }
}
