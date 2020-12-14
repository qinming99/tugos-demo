package cn.tugos.webservice.demo.service;

import cn.tugos.webservice.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author qinming
 * @date 2020-12-10 10:28:06
 * <p> 无 </p>
 */
@WebService(serviceName = "messageService",//对外发布的服务名
        targetNamespace = "http://demo.webservice.tugos.cn",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface = "cn.tugos.webservice.demo.service.WebServiceMsgService")
@Service
@Slf4j
public class WebServiceMsgServiceImpl implements WebServiceMsgService {

    @Override
    public ResultVO sendMsg(String name, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("name:"+name+",message:"+message);
        return resultVO;
    }
}

