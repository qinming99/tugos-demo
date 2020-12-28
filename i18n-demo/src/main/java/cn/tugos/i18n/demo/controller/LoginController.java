package cn.tugos.i18n.demo.controller;

import cn.tugos.i18n.demo.config.MessageConfig;
import cn.tugos.i18n.demo.enums.LoginEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinming
 * @date 2020-12-28 19:31:09
 * <p> 无 </p>
 */
@RestController
public class LoginController {


    /**
     * 获取枚举中的提示信息
     */
    @GetMapping("/login1")
    public String login1() {
        return "login1  " + LoginEnum.getMessage(1);
    }


    /**
     * 根据key获取配置文件中的信息
     */
    @GetMapping("/login2")
    public String login2() {
        return "login2  " + MessageConfig.getMessage("success.message");
    }


}
