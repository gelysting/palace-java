package com.flysnow.palace.basics.others.encode;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Package com.flysnow.palace.basics.others.encode
 * @Description 二、八、十、十六进制的字符串转换等方法。
 * @Author Fly
 * @Date 2019-11-04 14:47
 * @Version V1.0
 */
public class HexTool {


    //--------------------------------------------------------------------------------以下为进制转化
    /**
     * 字符串：二进制 TO 八进制
     * @param value
     * @return
     */
    public static String binToOctal(String value) {
        if (!value.isEmpty()) {
            return Integer.toOctalString(Integer.valueOf(value, 2));
        } else {
            return value;
        }
    }
    /**
     * 字符串：二进制 To 十进制
     * @param value
     * @return
     */
    public static String binToDec(String value) {
        if (!value.isEmpty()) {
            return Integer.toString(Integer.valueOf(value, 2));
        } else {
            return value;
        }
    }
    /**
     * 字符串：二进制 TO 十六进制
     * @param value
     * @return
     */
    public static String binToHex(String value) {
        if (!value.isEmpty()) {
            return Integer.toHexString(Integer.valueOf(value, 2)).toUpperCase();
        } else {
            return value;
        }
    }


    /**
     * 字符串：八进制 To 二进制
     * @param value
     * @return
     */
    public static String octalToBin(String value) {
        if (!value.isEmpty()) {
            return Integer.toBinaryString(Integer.valueOf(value, 8));
        } else {
            return value;
        }
    }
    /**
     * 字符串：八进制 To 十进制
     * @param value
     * @return
     */
    public static String octalToDec(String value) {
        if (!value.isEmpty()) {
            return Integer.toOctalString(Integer.valueOf(value, 8));
        } else {
            return value;
        }
    }
    /**
     * 字符串：八进制 To 十六进制
     * @param value
     * @return
     */
    public static String octalToHex(String value) {
        if (!value.isEmpty()) {
            return Integer.toString(Integer.valueOf(value, 8));
        } else {
            return value;
        }
    }


    /**
     * 字符串：十进制 To 二进制
     * @param value
     * @return
     */
    public static String decToBin(String value) {
        if (!value.isEmpty()) {
            return Integer.toBinaryString(Integer.valueOf(value, 10));
        } else {
            return value;
        }
    }
    /**
     * 字符串：十进制 To 八进制
     * @param value
     * @return
     */
    public static String decToOctal(String value) {
        if (!value.isEmpty()) {
            return Integer.toOctalString(Integer.valueOf(value, 10));
        } else {
            return value;
        }
    }
    /**
     * 字符串：十进制 To 十六进制
     * @param value
     * @return
     */
    public static String decToHex(String value) {
        if (!value.isEmpty()) {
            return Integer.toHexString(Integer.valueOf(value, 10));
        } else {
            return value;
        }
    }


    /**
     * 字符串：十六进制 To 二进制
     * @param value
     * @return
     */
    public static String hexToBin(String value) {
        if (!value.isEmpty()) {
            return Integer.toBinaryString(Integer.valueOf(value, 16));
        } else {
            return value;
        }
    }
    /**
     * 字符串：十六进制 To 八进制
     * @param value
     * @return
     */
    public static String hexToOctal(String value) {
        if (!value.isEmpty()) {
            return Integer.toOctalString(Integer.valueOf(value, 16));
        } else {
            return value;
        }
    }
    /**
     * 字符串：十六进制 To 十进制
     * @param value
     * @return
     */
    public static String hexToDec(String value) {
        if (!value.isEmpty()) {
            return Integer.toString(Integer.valueOf(value, 16));
        } else {
            return value;
        }
    }


    //--------------------------------------------------------------------------------以下为ASCII转化
    /**
     * 将字符串转成ASCII的Java方法
     * @param value
     * @return ASCII-a,ASCII-b,
     */
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }
    /**
     * 将ASCII转成字符串的java方法
     * @param value：ASCII-a,ASCII-b,
     * @return
     */
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }
    /**
     * ASCII 字符 转 数字（十进制）
     * byte[] = String.getBytes() 的实质就是，将ASCII的字符串转化十进制数值
     * @param str
     * @return
     */
    public static int asciiToInt(String str){
        byte[] bytes = str.getBytes();
        int sum = 0;
        for(int i=0; i<bytes.length; i++){
            sum += bytes[i];
        }
        return sum;
    }
    /**
     * 数字 转 ASCII 字符
     * @param value
     * @return
     */
    public static String intToAscii(int value){
        //System.out.print((char)Integer.parseInt(Integer.toString(value)));
        //return String.valueOf( (char)Integer.parseInt( String value ) );
        return String.valueOf( (char)value );
    }


    //--------------------------------------------------------------------------------以下为byte[]操作
    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从 Character.MIN_RADIX 到 Character.MAX_RADIX，超出范围后变为10进制，即范围是2~36
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    //--------------------------------------------------------------------------------以下为测试区域
    public static void main(String[] args){
        String str = "A3";
        int result = asciiToInt(str);
        System.out.println("ASCII to int：" + str + "=>" + result);
        System.out.println("int to ASCII：" + result + "=>" + intToAscii(result));

        String s = "12";
        byte[] bytes = s.getBytes(); //将字符串的每一位按照ASCII对照获取对应的十进制数
        System.out.println("转为字节数组："+Arrays.toString(bytes));
        System.out.println("可以转换的进制范围：" + Character.MIN_RADIX + "-" + Character.MAX_RADIX);
        //System.out.println("2进制："   + binary(bytes, 2));
        //System.out.println("5进制："   + binary(bytes, 5));
        //System.out.println("8进制："   + binary(bytes, 8));
        //System.out.println("10进制："  + binary(bytes, 10));
        //System.out.println("16进制："  + binary(bytes, 16));
        //System.out.println("32进制："  + binary(bytes, 32));
        //System.out.println("64进制："  + binary(bytes, 64));// 这个已经超出范围，超出范围后变为10进制显示
        //System.exit(0);

        System.out.println(hexToDec(s));

        //[49,50] -> [00110001,00110010] -> 12594，实际上就是将排在前面的字节按高八位处理，然后串联成二进制字符串再转化为十进制数值
        BigInteger bigInteger = new BigInteger(bytes);
        System.out.println(bigInteger);
        System.out.println(bigInteger.toString());
    }
}
