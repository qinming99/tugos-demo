package cn.tugos.i18n.demo.enums;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author qinming
 * @date 2020-12-28 19:27:44
 * <p> 无 </p>
 */
public enum LoginEnum {

    /**
     * 成功
     */
    SUCCESS(1, "成功", "success"),
    ;

    public Integer code;

    public String desc;

    public String descEn;

    LoginEnum(Integer code, String desc, String descEn) {
        this.code = code;
        this.desc = desc;
        this.descEn = descEn;
    }

    public static String getMessage(Integer key) {
        LoginEnum loginEnum = null;
        LoginEnum[] values = LoginEnum.values();
        for (LoginEnum object : values) {
            if (object.code.equals(key)) {
                loginEnum = object;
            }
        }
        //获取当前语言环境
        Locale locale = LocaleContextHolder.getLocale();
        if (!Locale.CHINA.getLanguage().equals(locale.getLanguage())
                && !Locale.ENGLISH.getLanguage().equals(locale.getLanguage())) {
            //其他语言一律为中文
            locale = Locale.CHINA;
        }
        if (loginEnum == null) {
            return "未知";
        }
        if (locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
            return loginEnum.desc;
        } else {
            return loginEnum.descEn;
        }
    }

}
