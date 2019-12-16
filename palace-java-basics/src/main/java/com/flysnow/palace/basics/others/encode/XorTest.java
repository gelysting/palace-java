package com.flysnow.palace.basics.others.encode;

/**
 * @Package com.flysnow.palace.basics.others.encode
 * @Description
 * @Author Fly
 * @Date 2019-11-02 1:45
 * @Version V1.0
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class XorTest {

    /**
     * int转byte数组
     *
     * @param
     * @return
     */
    public byte[] IntToByte(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        String content = "Hello";        // 原文内容

        /**
         *
         * 十六进制 = 十进制 = 二进制
         * 0xA3 = 163 = ?10100011?
         *?10100011?
         */
        String skey = "A3";
        Integer ikey = 163;
        String hexSkey = "10100011";

        byte[] bytes = skey.getBytes("UTF-8");
        System.out.println( "skey=>byte[]：" + skey + "=>" + Arrays.toString(bytes) );


        //System.out.println("ikey[" + ikey.toString() + "]=" + String.valueOf(Integer.toHexString(ikey)).toUpperCase()); //十进制数值转为十六进制字符串
        //System.out.println("ikey[" + ikey.toString() + "]=" + Integer.toBinaryString(ikey));  //十进制数值转为二进制字符串
        //
        //System.out.println("skey[" + skey + "]=" + Integer.toBinaryString(Integer.valueOf(skey, 16)));  //十六进制字符串转为二进制字符串
        //System.out.println("skey[" + skey + "]=" + Integer.valueOf(skey, 16));  //十六进制字符串转为十进制数值
        //
        //System.out.println("hexSkey[" + hexSkey + "]=" + Integer.valueOf(hexSkey, 2).toString());  //二进制字符串转为十进制数值
        //System.out.println("hexSkey[" + hexSkey + "]=" + Integer.toHexString(Integer.valueOf("101000111010001110100011", 2)).toUpperCase());  //二进制字符串转为十六进制字符串
        //
        //System.out.println(Arrays.toString("A3".getBytes())); //获得字符串的字节数组，并打印输出
        //System.out.println(Arrays.toString("a3".getBytes())); //获得字符串的字节数组，并打印输出
        //
        //
        //System.out.println("==>" + (char) Integer.parseInt( "65" ) ); //十进制字符串-》ASCII码
        //System.out.println("==>" + (char) 65);
        //
        //System.out.println("==>" + Integer.toHexString(10) ); //十进制数值-》十六进制字符串
        //System.out.println("==>" + Integer.toOctalString(10) ); //十进制数值-》八进制字符串
        //System.out.println("==>" + Integer.toBinaryString(10) ); //十进制数值-》二进制字符串
        //
        //System.out.println("==>" + Integer.valueOf("A3", 16) ); //指定输入字符串为十六进制字符串，获得对应的十进制Integer对象值
        //System.out.println("==>" + Integer.parseInt("A3",16) ); //指定输入字符串为十六进制字符串，获得对应的十进制Int整型值
        //System.out.println("==>" + Integer.toBinaryString( Integer.parseInt("A3",16) ) ); //指定输入字符串为十六进制字符串，获得对应的十进制Int整型值，再获得对应的二进制字符串


        byte[] contBytes = content.getBytes("UTF-8");
        System.out.println("Hello->byte[]："+Arrays.toString(contBytes));

        /**********************/
        byte k = (byte) 0xA3;  // => -93
        byte[] resultBytes = new byte[contBytes.length];
        for (int i = 0; i < contBytes.length; i++) {
            resultBytes[i] =  (byte)(contBytes[i] ^ k ^ (i & 0xFF) ); //增加异或 0xFF 目的是为了将高4位进行降维，使异或结果值不超出byte的范围-128~127
            //contBytes[i]^=k;
        }
        contBytes = resultBytes;
        System.out.println("1===>"+Arrays.toString(contBytes));
        System.out.println("1===>"+new String(contBytes));

        for (int i = 0; i < contBytes.length; i++) {
            resultBytes[i] =  (byte)(contBytes[i] ^ k ^ (i & 0xFF) );
            //contBytes[i]^=k;
        }
        contBytes = resultBytes;
        System.out.println("2===>"+Arrays.toString(contBytes));
        System.out.println("2===>"+new String(contBytes));
        /**********************/


        //try{
        //    //File inFile = new File("fe.jpg");
        //    //File outFile = new File("out.jpg");
        //
        //    File inFile = new File("out.jpg");
        //    File outFile = new File("outback.jpg");
        //
        //    DataInputStream dis = new DataInputStream( new FileInputStream(inFile));
        //    DataOutputStream dos = new DataOutputStream( new FileOutputStream(outFile));
        //    byte[] by = new byte[1024];
        //    int len;
        //    int cnt = 0;
        //    while((len=dis.read(by))!=-1){
        //        for(int i=0;i<len;i++){
        //            by[i]^=0xa3;
        //        }
        //        System.out.println("cnt=" + ++cnt);
        //        dos.write(by,0,len);
        //    }
        //    dis.close();
        //    dos.close();
        //}catch(IOException ioe){
        //    System.out.println(ioe);
        //}


        //int ia = 2;
        //int ib = 3;
        //int ic = ia ^ ib;
        //byte ba = 2;
        //byte bb = 3;
        //byte bc = (byte)( ba ^ bb );

        //// 加密数据, 返回密文
        //byte[] cipherBytes = XORUtils.encrypt(content.getBytes(), key.getBytes());
        //// 解密数据, 返回明文
        //byte[] plainBytes = XORUtils.encrypt(cipherBytes, key.getBytes());
        //// 输出解密后的明文: "Hello world!"
        //System.out.println("明码：" + content);
        //System.out.println("解码：" + new String(plainBytes));


        /*
         * XOR 对文件的加密/解密
         */
        //// 将 文件demo.java 加密后输出到 文件demo.jpg_cipher
        //XORUtils.encryptFile(new File("demo.jpg"), new File("demo.jpg_cipher"), key.getBytes());
        //// 将 文件demo.jpg_cipher 解密输出到 文件demo.jpg_plain
        //XORUtils.encryptFile(new File("demo.jpg_cipher"), new File("demo.jpg_plain"), key.getBytes());

        // 对比 原文件demo.jpg 和 解密得到的文件demo.jpg_plain 两者的 MD5 将会完全相同
    }


}
