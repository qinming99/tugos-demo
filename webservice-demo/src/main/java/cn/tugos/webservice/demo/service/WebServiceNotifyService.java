package cn.tugos.webservice.demo.service;

import cn.tugos.webservice.demo.vo.ResultVO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author qinming
 * @date 2020-12-14 19:24:44
 * <p> 无 </p>
 */
@WebService
public interface WebServiceNotifyService {

    @WebMethod
    ResultVO notify(@WebParam(name = "userId") Integer userId, @WebParam(name = "message") String message);


}
