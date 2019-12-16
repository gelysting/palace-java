package com.flysnow.palace.basics.javaBase.clone;

import java.io.Serializable;

/**
 * @Package com.flysnow.palace.basics.javaBase.clone
 * @Description 封装序列化和反序列化操作
 * @Author Fly
 * @Date 2019-11-26 14:55
 * @Version V1.0
 */
public class CloneUtil {
    private CloneUtil() {}

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T cloneObject(T obj) {
        // 序列化
        byte[] bs = SerializeUtil.serialize(obj);

        // 反序列化
        return (T) SerializeUtil.unserialize(bs);
    }
}
