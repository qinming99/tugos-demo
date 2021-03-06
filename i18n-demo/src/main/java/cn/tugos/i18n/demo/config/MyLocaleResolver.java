package cn.tugos.i18n.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author qinming
 * @date 2020-12-28 16:56:13
 * <p> 无 </p>
 */
public class MyLocaleResolver implements LocaleResolver {

    private static final String LANG = "lang";

    private static final String LANG_SESSION = "lang_session";

    public static final String DELIMITER = "_";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader(LANG);
        //默认语言 简体中文
        Locale locale = Locale.CHINA;
        if (StringUtils.isNotBlank(lang) && lang.contains(DELIMITER)) {
            String[] langueagea = lang.split(DELIMITER);
            locale = new Locale(langueagea[0], langueagea[1]);
            HttpSession session = request.getSession();
            session.setAttribute(LANG_SESSION, locale);
        } else {
            HttpSession session = request.getSession();
            Locale localeInSession = (Locale) session.getAttribute(LANG_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }


}
