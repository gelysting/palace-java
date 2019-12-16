package com.flysnow.palace.basics.javaIoNet;

/**
 * @Package cn.knight.fly.test.file
 * @Description
 * @Author Fly
 * @Date 2019-11-19 15:55
 * @Version V1.0
 */
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

/**
 * 文件普通读写流与文件通道测试
 * FileChannel和Stream的使用方式和效率对比
 *
 * Channel是对I/O操作的封装。
 *
 * FileChannel配合着ByteBuffer，将读写的数据缓存到内存中，然后以批量/缓存的方式read/write，省去了非批量操作时的重复中间操作，操纵大文件时可以显著提高效率（和Stream以byte数组方式有什么区别？经过测试，效率上几乎无区别）。
 *
 * 不过对于运行在容器中的应用需要考虑GC，而ByteBuffer可以使用直接内存（系统内存）（allocateDirect），使用后无需jvm回收。
 *
 * ByteBuffer还有一个子类MappedByteBuffer可以直接将文件映射到操作系统的虚拟内存，读写文件速度会更快，参考https://www.cnblogs.com/lyftest/p/6564282.html。
 *
 */
public class FileChannelTest {

    public static void main(String[] args) {
        // 4GB的数据
        File sourceFile = new File("d://dd.iso");
        File targetFile = new File("d://ee.iso");
        targetFile.deleteOnExit();
        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // stream方式
        FileChannelTest.copyFileByStream(sourceFile, targetFile);

        // channel方式
        //FileChannelTest.copyFileByFileChannel(sourceFile, targetFile);
    }

    /**
     * channel方式
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFileByFileChannel(File sourceFile, File targetFile) {
        Instant begin = Instant.now();

        RandomAccessFile randomAccessSourceFile;
        RandomAccessFile randomAccessTargetFile;
        try {
            // 构造RandomAccessFile，用于获取FileChannel
            randomAccessSourceFile = new RandomAccessFile(sourceFile, "r");
            randomAccessTargetFile = new RandomAccessFile(targetFile, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        FileChannel sourceFileChannel = randomAccessSourceFile.getChannel();
        FileChannel targetFileChannel = randomAccessTargetFile.getChannel();

        // 分配1MB的缓存空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        try {
            while (sourceFileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                targetFileChannel.write(byteBuffer);
                byteBuffer.clear();  //byteBuffer.compact(); //或者可以调用compact()方法清空缓存

                /**
                 * 向Buffer中写数据：
                 *
                 * 从Channel写到Buffer (fileChannel.read(buf))
                 *
                 * 通过Buffer的put()方法 buf.put(…)
                 *
                 * 从Buffer中读取数据：
                 *
                 * 从Buffer读取到Channel (channel.write(buf))
                 *
                 * 使用get()方法从Buffer中读取数据 buf.get()
                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sourceFileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                targetFileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("total spent " + Duration.between(begin, Instant.now()).toMillis());
    }

    /**
     * stream方式
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFileByStream(File sourceFile, File targetFile) {
        Instant begin = Instant.now();

        FileInputStream fis;
        FileOutputStream fos;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        // 使用byte数组读取方式，缓存1MB数据
        byte[] readed = new byte[1024 * 1024];
        try {
            while (fis.read(readed) != -1) {
                fos.write(readed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("total spent " + Duration.between(begin, Instant.now()).toMillis());
    }
}