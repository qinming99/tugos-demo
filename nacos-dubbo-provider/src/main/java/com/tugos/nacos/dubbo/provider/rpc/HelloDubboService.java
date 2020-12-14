package com.tugos.nacos.dubbo.provider.rpc;

import com.tugos.nacos.dubbo.api.IHelloDubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author qinming
 * @date 2020-12-10 09:21:41
 * <p> 无 </p>
 */
@Service
@Slf4j
public class HelloDubboService  implements IHelloDubboService {

    @Override
    public void say(String message) {
        log.info("来自dubbo的问候：{}",message);
    }


}
