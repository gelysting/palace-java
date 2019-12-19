package com.flysnow.palace.basics.others;

/**
 * @Package com.flysnow.palace.basics.others
 * @Description 资源路径获取
 * @Author Fly
 * @Date 2019-12-19 11:17
 * @Version V1.0
 */
public class ResourcePath {

    public static void main(String[] args) {
        // 1、通过Class的getResource方法
        String a1 = ResourcePath.class.getResource("/com/alipay/Resource.class").getPath();
        String a2 = ResourcePath.class.getResource("Resource.class").getPath();
        String a3 = ResourcePath.class.getResource("/request.xml").getPath();
        String a4 = ResourcePath.class.getResource("../../request.xml").getPath();
        String a5 = ResourcePath.class.getResource("/conf/sysConf.json").getPath();
        String a6 = ResourcePath.class.getResource("../../conf/sysConf.json").getPath();

        // 2、通过本类的ClassLoader的getResource方法
        String b1 = ResourcePath.class.getClassLoader().getResource("com/alipay/Resource.class").getPath();
        String b2 = ResourcePath.class.getClassLoader().getResource("request.xml").getPath();
        String b3 = ResourcePath.class.getClassLoader().getResource("conf/sysConf.json").getPath();

        // 3、通过ClassLoader的getSystemResource方法
        String c1 = ClassLoader.getSystemClassLoader().getResource("com/alipay/Resource.class").getPath();
        String c2 = ClassLoader.getSystemClassLoader().getResource("request.xml").getPath();
        String c3 = ClassLoader.getSystemClassLoader().getResource("conf/sysConf.json").getPath();

        // 4、通过ClassLoader的getSystemResource方法
        String d1 = ClassLoader.getSystemResource("com/alipay/Resource.class").getPath();
        String d2 = ClassLoader.getSystemResource("request.xml").getPath();
        String d3 = ClassLoader.getSystemResource("conf/sysConf.json").getPath();

        // 5、通过Thread方式
        String e1 = Thread.currentThread().getContextClassLoader().getResource("com/alipay/Resource.class").getPath();
        String e2 = Thread.currentThread().getContextClassLoader().getResource("request.xml").getPath();
        String e3 = Thread.currentThread().getContextClassLoader().getResource("conf/sysConf.json").getPath();
    }
}
