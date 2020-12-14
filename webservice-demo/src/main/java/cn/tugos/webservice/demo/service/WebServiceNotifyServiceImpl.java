package cn.tugos.webservice.demo.service;

import cn.tugos.webservice.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author qinming
 * @date 2020-12-14 19:26:49
 * <p> 无 </p>
 */
@WebService(serviceName = "notifyService",//对外发布的服务名
        targetNamespace = "http://demo.webservice.tugos.cn",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface = "cn.tugos.webservice.demo.service.WebServiceNotifyService")//服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
@Service
@Slf4j
public class WebServiceNotifyServiceImpl implements WebServiceNotifyService{

    @Override
    public ResultVO notify(Integer userId, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("userId:"+userId+",message:"+message);
        return resultVO;
    }
}
