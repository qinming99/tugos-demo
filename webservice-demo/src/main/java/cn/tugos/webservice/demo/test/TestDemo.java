package cn.tugos.webservice.demo.test;

import cn.hutool.http.webservice.SoapClient;
import cn.hutool.json.JSONUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinming
 * @date 2020-12-14 19:51:03
 * <p> 测试webservice </p>
 */
public class TestDemo {


    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/myService/msgService?wsdl";
        Object[] str = {"qinming", "你好啊"};
        String method = "sendMsg";
        dcfSoap(url, method, str);

        HashMap<String, Object> params = new HashMap<>(16);
        params.put("userId", 666);
        params.put("message", "你好！666");
        //方法要带前缀，命名空间最后的斜杠要带上
        soapUtil("http://localhost:8080/myService/notifyService?wsdl",
                "web:notify", "http://service.demo.webservice.tugos.cn/", params);

    }


    /**
     * 动态调用webservice
     */
    public static void dcfSoap(String url, String method, Object[] str) throws Exception {
        /// 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(url);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        QName name = new QName("http://service.demo.webservice.tugos.cn/", method);
        HashMap<String, Object> map = new HashMap<>(16);
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            Object[] objects = client.invoke(name, str);
            for (Object object : objects) {
                System.out.println(JSONUtil.toJsonStr(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过htool根据类调用
     */
    public static void soapUtil(String url, String method, String nameSpace, Map<String, Object> params) {
        //接口地址
        SoapClient soapClient = SoapClient.create(url);
        //调用的方法，命名空间
        soapClient.setMethod(method, nameSpace);
        //false不带前缀
        soapClient.setParams(params, false);
        //格式化返回
        String send = soapClient.send(true);
        System.out.println(send);
    }


}
