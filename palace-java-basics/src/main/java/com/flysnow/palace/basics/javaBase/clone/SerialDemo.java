package com.flysnow.palace.basics.javaBase.clone;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description
 * @Author Fly
 * @Date 2019-11-26 14:58
 * @Version V1.0
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化和反序列化，对象持久化到文件或从文件中读取对象
 *
 * @author oy
 * @version 1.0
 * @date 2018年8月9日
 * @time 下午8:58:32
 */
public class SerialDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 创建Person对象
        Car car = new Car("BMW", "black");
        PersonB p = new PersonB("张三abc", 20, car);

        write(p);
        Object obj = read();

        // Person [name=张三abc, age=20, car=Car [name=BMW, color=black]]
        System.out.println(obj);

    }

    private static Object read() throws FileNotFoundException, IOException, ClassNotFoundException {
        // 创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\object.txt"));
        // 反序列化
        Object obj = ois.readObject();
        // 释放资源
        ois.close();
        return obj;
    }

    private static void write(Object obj) throws FileNotFoundException, IOException {
        // 创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\object.txt"));
        // 序列化
        oos.writeObject(obj);
        // 释放资源
        oos.close();
    }
}
