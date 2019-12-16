package com.flysnow.palace.basics.javaBase.clone;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:36
 * @Version V1.0
 */

public class StudentB implements Cloneable {
    private int age;
    private String name;
    private Teacher teacher;

    // getter和setter省略

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // 这一步返回的这个student还只是一个浅克隆，
        StudentB student = (StudentB) super.clone();
        // 然后克隆的过程中获得这个克隆的student，然后调用这个getTeacher这个方方法得到这个Teacher对象。
        // 然后实现克隆。在设置到这个student中的Teacher。
        // 这样实现了双层克隆使得那个teacher对象也得到了复制。
        student.setTeacher((Teacher) student.getTeacher().clone());
        // 双层克隆使得那个teacher对象也得到了复制
        return student;
    }
}
