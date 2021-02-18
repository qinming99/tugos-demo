package cn.tugos.logaop.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qinming
 * @date 2021-02-18 11:07:46
 * <p> 无 </p>
 */
public class HttpUtils {


    private static final String SIGN = ",";

    private static final String STR_UNKNOWN = "unknown";

    /**
     * 获取请求ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || STR_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip.contains(SIGN)) {
            return ip.split(SIGN)[0];
        } else {
            return ip;
        }
    }


}
