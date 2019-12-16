package cn.knight.fly.test.clone;

/**
 * @Package cn.knight.fly.test.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:27
 * @Version V1.0
 */
/*
    浅克隆(ShallowClone)和深克隆(DeepClone)。

    在Java语言中，数据类型分为值类型（基本数据类型）和引用类型，
    值类型包括int、double、byte、boolean、char等简单数据类型，
    引用类型包括类、接口、数组等复杂类型。
    浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制。

    实现clone方法的步骤
        （1）实现Cloneable接口
        （2）重写Object类中的clone()方法，重写时需定义为public
        （3）在重写方法中，调用super.clone()
 */
public class Student implements Cloneable {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /*
    @Override
    public Object clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
    */
}