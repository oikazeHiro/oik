package com.oik.util.exception;

import lombok.*;

/**
 * 自定义全局返回结果格式
 *
 * @author oik
 * &#064;date  2021/7/23 15:35
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
    private Long count = 0L;
}
