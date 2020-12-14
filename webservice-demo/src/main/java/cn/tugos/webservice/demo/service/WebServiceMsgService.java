package cn.tugos.webservice.demo.service;

import cn.tugos.webservice.demo.vo.ResultVO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author qinming
 * @date 2020-12-10 10:27:21
 * <p> 无 </p>
 */
@WebService
public interface WebServiceMsgService {

    @WebMethod
    ResultVO sendMsg(@WebParam(name = "name") String name,@WebParam(name = "message") String message);


}
