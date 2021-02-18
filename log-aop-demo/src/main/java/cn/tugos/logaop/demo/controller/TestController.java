package cn.tugos.logaop.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinming
 * @date 2021-02-18 11:19:53
 * <p> 无 </p>
 */
@RestController
public class TestController {


    @PostMapping("/test")
    public Map<String, Object> test(@RequestBody Map<String, String> requestParam) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("requestParam", requestParam);
        return result;
    }

}
