package cn.knight.fly.test.xencode;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.lang.Byte;
import java.util.Arrays;

/**
 * @Package cn.knight.fly.test.xencode
 * @Description byte[]转二进制字符串,十六进制字符串的相互转换

1.byte的大小为8bits而int的大小为32bits
2.java的二进制采用的是补码形式

计算机基础理论:
byte是一个字节保存的，有8个位，即8个0、1。
8位的第一个位是符号位，
也就是说0000 0001代表的是数字1
1000 0000代表的就是-1
所以正数最大位0111 1111，也就是数字127
负数最大为1111 1111，也就是数字-128

上面说的是二进制原码，但是在java中采用的是补码的形式，下面介绍下什么是补码

1、反码：
一个数如果是正，则它的反码与原码相同；
一个数如果是负，则符号位为1，其余各位是对原码取反；

2、补码：利用溢出，我们可以将减法变成加法
对于十进制数，从9得到5可用减法：
9－4＝5    因为4+6＝10，我们可以将6作为4的补数
改写为加法：
9+6＝15（去掉高位1，也就是减10）得到5.

对于十六进制数，从c到5可用减法：
c－7＝5    因为7+9＝16 将9作为7的补数
改写为加法：
c+9＝15（去掉高位1，也就是减16）得到5.

在计算机中，如果我们用1个字节表示一个数，一个字节有8位，超过8位就进1，在内存中情况为（100000000），进位1被丢弃。

⑴一个数为正，则它的原码、反码、补码相同
⑵一个数为负，则符号位为1，其余各位是对原码取反，然后整个数加1

- 1的原码为                10000001
- 1的反码为                11111110
                                + 1
- 1的补码为                11111111

0的原码为                 00000000
0的反码为                 11111111（正零和负零的反码相同）
                                +1
0的补码为               100000000（舍掉打头的1，正零和负零的补码相同）

Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111好多1啊，呵呵！即0xffffffff但是这个数是不对的，这种补位就会造成误差。
和0xff相与后，高24比特就会被清0了，结果就对了。

----
Java中的一个byte，其范围是-128~127的，而Integer.toHexString的参数本来是int，如果不进行&0xff，那么当一个byte会转换成int时，
对于负数，会做位扩展，举例来说，一个byte的-1（即0xff），会被转换成int的-1（即0xffffffff），那么转化出的结果就不是我们想要的了。

而0xff默认是整形，所以，一个byte跟0xff相与会先将那个byte转化成整形运算，这样，结果中的高的24个比特就总会被清0，于是结果总是我们想要的。


 * @Author Fly
 * @Date 2019-11-02 13:40
 * @Version V1.0
 */


public class HexConver {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "A3";
        System.out.println("原字符串：" + content);
        System.out.println("原字符串的字节数组：" + Arrays.toString( content.getBytes() ) );

        String hex2Str = conver2HexStr(content.getBytes());
        System.out.println("\n二进制的表示形式：" + hex2Str);
        byte[] b = conver2HexToByte(hex2Str);
        System.out.println("二进制字符串还原：" + new String(b));

        String hex16Str = conver16HexStr(content.getBytes());
        System.out.println("\n十六进制的表示形式:" + hex16Str);
        System.out.println("十六进制字符串还原:" + new String(conver16HexToByte(hex16Str),"utf-8"));

        String hex16Str2 = conver16HexStr(content.getBytes("Unicode"));
        System.out.println("\n十六进制的Unicode表示形式:" + hex16Str2);
        System.out.println("十六进制字符串还原:" + new String(conver16HexToByte(hex16Str2),"Unicode"));

        System.out.println( "\n十进制数值转为ASCII码：" + int2Ascii(65) );
        System.out.println( "ASCII码转为十进制数值：" + ascii2Int("AB") + ">>" + ascii2Int("A") + ">>" + ascii2Int("B") );

        byte bbyte = (byte) 0xA3;
        System.out.println("\n十六进制表示法定义的byte：" + bbyte);

        byte[] keys =  "A3".getBytes();
        for (byte bb : keys) {
            System.out.println("字符串：" + bb );
        }

        /*
        原字符串：A3
        原字符串的字节数组：[65, 51]

        二进制的表示形式：1000001,110011
        二进制字符串还原：A3

        十六进制的表示形式:4133
        十六进制字符串还原:A3

        十六进制的Unicode表示形式:FEFF00410033
        十六进制字符串还原:A3

        十进制数值转为ASCII码：A
        ASCII码转为十进制数值：131>>65>>66
        */
    }

    /**
     * byte数组转换为二进制字符串,每个字节以","隔开
     *
     * @param b
     * @return
     */
    public static String conver2HexStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 0xff, 2) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }

    /**
     * 二进制字符串转换为byte数组,每个字节以","隔开
     *
     * @param hex2Str
     * @return
     */
    public static byte[] conver2HexToByte(String hex2Str) {
        String[] temp = hex2Str.split(",");
        byte[] b = new byte[temp.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }


    /**
     * byte数组转换为十六进制的字符串
     *
     * @param b
     * @return
     */
    public static String conver16HexStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            if ((b[i] & 0xff) < 0x10)
                result.append("0");
            result.append(Long.toString(b[i] & 0xff, 16));
        }
        return result.toString().toUpperCase();
    }

    /**
     * 十六进制的字符串转换为byte数组
     *
     * @param hex16Str
     * @return
     */
    public static byte[] conver16HexToByte(String hex16Str) {
        char[] c = hex16Str.toCharArray();
        byte[] b = new byte[c.length / 2];
        for (int i = 0; i < b.length; i++) {
            int pos = i * 2;
            b[i] = (byte) ("0123456789ABCDEF".indexOf(c[pos]) << 4 | "0123456789ABCDEF".indexOf(c[pos + 1]));
        }
        return b;
    }

    /********************************************************************/
    /** 以下内容为： */

    /** Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *  byte字节数组 转为 十六进制字符串
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);

            //长度等于1，即少于2的时候，需要在前面补零
            if (hv.length() == 1 ) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * Convert hex string to byte[]
     *  十六进制字符串 转为 byte字节数组
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *  char字符 转为 byte字节
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 将指定byte数组以16进制的形式打印到控制台
     * @param b
     */
    public static void printHexString( byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() );
        }

    }


    /************************************************************************/
    /*** 以下内容为： byte[]字节数组与二进制字符串\十六进制字符串的相互转换 ****/

    /**
     * @author Fly
     * @Description 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813，
     *          和public static byte[]hexStrToByteArr(String strIn) 互为可逆的转换过程
     * @date 2019-11-02 18:02
     * @param arrB
     * @return
     * @throws Exception
     */
    private static String byteArrToHexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * @author Fly
     * @Description 将表示16进制值的字符串转换为byte数组
     *          和public static String byteArr2HexStr(byte[] arrB) 互为可逆的转换过程
     * @date 2019-11-02 18:01
     * @param strIn
     * @return
     * @throws Exception
     */
    private static byte[] hexStrToByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * @author Fly
     * @Description 二进制字符串转换为byte数组,每个字节以","隔开
     * @date 2019-11-02 17:54
     * @param binStr
     * @return
     */
    public static byte[] binStrToByteArr(String binStr) {
        String[] temp = binStr.split(",");
        byte[] b = new byte[temp.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }

    /**
     * @author Fly
     * @Description byte数组转换为二进制字符串,每个字节以","隔开
     * @date 2019-11-02 17:55
     * @param b
     * @return
     */
    public static String byteArrToBinStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 0xff, 2) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }

    /**
     * @author Fly
     * @Description byte数组转换为普通字符串
     * @date 2019-11-02 18:00
     * @param b
     * @return
     * @throws Exception
     */
    public static String byteArrToStr(byte[] b) throws Exception {
        String str = new String(b,"UTF-8");
        return str;
        //普通字符串转换为byte数组: byte[] b = str.getBytes("UTF-8")
    }



    /**************************************************************/

    /**
     * 将字符串转成ASCII的Java方法
     * @param value
     * @return
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
     * @param value
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
     * ASCII 字符 转 数字
     * @param str
     * @return
     */
    public static int ascii2Int(String str){
        byte[] bytes = str.getBytes();
        int sum = 0;
        for(int i=0; i<bytes.length; i++){
            sum += bytes[i];
        }
        return sum;
    }

    /**
     * 数字 转 ASCII 字符
     * 十进制字符串-》ASCII码
     * @param value
     * @return
     */
    public static String int2Ascii(int value){
        //System.out.print((char)Integer.parseInt(Integer.toString(value)));
        //return String.valueOf( (char)Integer.parseInt( String value ) );
        return String.valueOf( (char)value );
    }

    /*****************************************************************/
    /** 以下内容：byte[]字节数组转换为二、八、十、十六进制字符串 **/

    public static void testByteConver() {
        String s = "woaini";
        byte[] bytes = s.getBytes();
        System.out.println("将woaini转为不同进制的字符串：");
        System.out.println("可以转换的进制范围：" + Character.MIN_RADIX + "-" + Character.MAX_RADIX);
        System.out.println("2进制："   + binary(bytes, 2));
        System.out.println("5进制："   + binary(bytes, 5));
        System.out.println("8进制："   + binary(bytes, 8));
        System.out.println("16进制："  + binary(bytes, 16));
        System.out.println("32进制："  + binary(bytes, 32));
        System.out.println("64进制："  + binary(bytes, 64));// 这个已经超出范围，超出范围后变为10进制显示
        System.exit(0);
    }
    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /*****************************************************************/

}
