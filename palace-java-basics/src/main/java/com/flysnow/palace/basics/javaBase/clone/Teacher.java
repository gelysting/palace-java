package cn.knight.fly.test.clone;

/**
 * @Package cn.knight.fly.test.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:35
 * @Version V1.0
 */

class Teacher implements Cloneable {
    private int age;
    private String name;

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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
