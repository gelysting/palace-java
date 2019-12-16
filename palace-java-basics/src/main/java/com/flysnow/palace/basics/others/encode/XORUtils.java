package com.flysnow.palace.basics.others.encode;

/**
 * @Package com.flysnow.palace.basics.others.encode
 * @Description
 * @Author Fly
 * @Date 2019-11-02 1:39
 * @Version V1.0
 */

import java.io.*;

/**
 * 异或(xor)算法加密/解密工具
 *
 * @author xietansheng
 */
public class XORUtils {

    /**
     * 异或算法加密/解密
     *
     * @param data 数据（密文/明文）
     * @param key 密钥
     * @return 返回解密/加密后的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return data;
        }

        byte[] result = new byte[data.length];

        // 使用密钥字节数组循环加密或解密
        for (int i = 0; i < data.length; i++) {
            // 数据与密钥异或, 再与循环变量的低8位异或（增加复杂度）
            result[i] = (byte) (data[i] ^ key[i % key.length] /*^ (i & 0xFF)*/ );

            //System.err.println("=========>>" + result[i]);
        }

        return result;
    }

    /**
     * 对文件异或算法加密/解密
     *
     * @param inFile 输入文件（密文/明文）
     * @param outFile 结果输出文件
     * @param key 密钥
     */
    public static void encryptFile(File inFile, File outFile, byte[] key) throws Exception {
        InputStream in = null;
        OutputStream out = null;

        try {
            // 文件输入流
            in = new FileInputStream(inFile);
            // 结果输出流, 异或运算时, 字节是一个一个读取和写入, 这里必须使用缓冲流包装,
            // 等缓冲到一定数量的字节（10240字节）后再写入磁盘（否则写磁盘次数太多, 速度会非常慢）
            out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

            int b = -1;
            long i = 0;

            // 每次循环读取文件的一个字节, 使用密钥字节数组循环加密或解密
            while ((b = in.read()) != -1) {
                // 数据与密钥异或, 再与循环变量的低8位异或（增加复杂度）
                b = (b ^ key[(int) (i % key.length)] ^ (int) (i & 0xFF));
                // 写入一个加密/解密后的字节
                out.write(b);
                // 循环变量递增
                i++;
            }
            out.flush();

        } finally {
            //in.close();
            //out.close();
            close(in);
            close(out);
        }
    }

    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

    /***********************************************************************/

    /**
     * 打包
     *  在原内容上，最前增加1个字节，最后增加2个字节，第一个与最后一个字节分别存放固定值，
     *  倒数第二个字节则存贮原内容每一个字节逐渐与第一个字节异或后，以此类推的结果，最后得到的一个字节。
     * @param
     * @return
     * 逐个直接进行异或
     */
    public static byte[] wrapStr(String str) {
        byte[] bReturn = null;
        String strReturn = "";
        byte bFirst = 0x02;
        byte bEnd = 0x03;
        if (str != null) {
            if (str.length() == 0) {
                return null;
            }
            byte[] b = str.getBytes();
            int iLen = b.length;
            str = fillChar(iLen + "", '0', true, 4) + str;
            System.out.println("str=====" + str);

            byte[] bb = str.getBytes();
            int ii = bb.length;
            byte[] bAll = new byte[ii + 3];

            bAll[0] = bFirst;
            bAll[ii + 1] = bEnd;

            for (int i = 0; i < ii; i++) {
                bAll[i+1] = bb[i];
            }

            byte bTemp = bFirst;
            for (int i = 0; i < bAll.length - 2; i++) {
                bTemp = (byte) (bTemp ^ bAll[i]);
            }
            bAll[bAll.length - 1] = bTemp;
            bReturn = bAll;
        } else {
            return null;
        }
        System.out.println(bReturn.toString());
        for (int i = 0; i < bReturn.length - 1; i++) {
            System.out.println("abc=====" + bReturn);
        }
        return bReturn;
    }

    /**
     * 解包
     *
     * @param
     * @return
     */
    private static String fillChar(String str, char c, boolean orient,int totallenth) {
        if (str == null) {
            return fillChar("", ' ', false, totallenth);
        }
        byte[] b = str.getBytes();
        int strlen = b.length;
        int len = totallenth - strlen;
        for (int i = 0; i < len; i++) {
            if (orient == true) {
                str = c + str;
            } else {
                str = str + c;
            }
        }
        return str;
    }

    public static void main(String[] args){
        try{
            File inFile = new File("test.mp4");
            File outFile = new File("out.mp4");

            DataInputStream dis = new DataInputStream( new FileInputStream(inFile));
            DataOutputStream dos = new DataOutputStream( new FileOutputStream(outFile));
            byte[] by = new byte[1024];
            int len;
            while((len=dis.read(by))!=-1){
                for(int i=0;i<len;i++){
                    by[i]^=0xa3;
                }
                dos.write(by,0,len);
            }
            dis.close();
            dos.close();
        }catch(IOException ioe){
            System.err.println(ioe);
        }
    }

}
