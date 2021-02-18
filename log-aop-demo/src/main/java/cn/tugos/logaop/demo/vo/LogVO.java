package cn.tugos.logaop.demo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author qinming
 * @date 2021-02-18 11:32:20
 * <p> 日志记录vo </p>
 */
@Data
@Builder
public class LogVO {

    // ip
    private String ip;

    // url
    private String url;

    // 请求方式 GET POST PUT DELETE PATCH
    private String httpMethod;

    // 类方法
    private String classMethod;

    // 请求参数
    private Object requestParams;

    // 返回参数
    private Object result;

    // 接口耗时
    private Long timeCost;

}
