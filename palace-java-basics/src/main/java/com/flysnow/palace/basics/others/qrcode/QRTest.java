package com.flysnow.palace.basics.others.qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import sun.reflect.annotation.ExceptionProxy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Package com.flysnow.palace.basics.others.qrcode
 * @Description
 * @Author Fly
 * @Date 2019-11-24 22:04
 * @Version V1.0
 */
public class QRTest {
    public static void main(String[] args) throws Exception {
        String filename = "D:/MTemp/qrcode1.jpg";
        decode2(new File(filename));

        test1();
    }


    public static void test1() throws Exception {
        // 存放在二维码中的内容
        //8455HHHHjosgJHkIklsgsskssklqhgqjk
        //A811HHHHjosgJHkIklsgsskksqllstkgi
        String text = "https://dsfmember.ematong.com/wap/wxsuibaointegral/2192/A811HHHHjosgJHkIklsgsskksqllstkgi";

        // 嵌入二维码的图片路径
        String imgPath = "D:/MTemp/qrcode2.jpg";

        // 生成的二维码的路径及名称
        String destPath = "D:/MTemp/qrcode3.jpg";

        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);

        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

    public static void decode2(File file) {
        try {
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    System.out.println("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;

                //@SuppressWarnings("rawtypes")
                //Hashtable hints = new Hashtable();
                ////解码设置编码方式为：utf-8
                //hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

                Map<DecodeHintType, Object> hints = new LinkedHashMap<DecodeHintType, Object>();
                //解码设置编码方式为：utf-8，
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                //优化精度
                hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
                //复杂模式，开启PURE_BARCODE模式
                hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

                result = new MultiFormatReader().decode(bitmap, hints);
                String resultStr = result.getText();
                System.out.println("解析后内容：" + resultStr);
            } catch (IOException ioe) {
                System.out.println(ioe.toString());
            } catch (ReaderException re) {
                System.out.println(re.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

}
