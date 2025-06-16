package com.customer.ecommerce.common;

import lombok.Data;
import java.io.Serializable;

/**
 * 通用响应结果封装类
 * @param <T> a
 */
@Data
public class R<T> implements Serializable {

    private Integer code; // 编码：200表示成功，其他数字表示失败

    private String msg; // 错误信息

    private T data; // 数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.data = object;
        r.code = 200;
        r.msg = "操作成功";
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 500;
        return r;
    }
}
