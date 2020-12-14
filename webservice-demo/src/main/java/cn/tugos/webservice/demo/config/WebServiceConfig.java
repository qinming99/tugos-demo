package cn.tugos.webservice.demo.config;

import cn.tugos.webservice.demo.service.WebServiceMsgService;
import cn.tugos.webservice.demo.service.WebServiceNotifyService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
/**
 * @author qinming
 * @date 2020-12-10 10:29:22
 * <p> webservice配置 </p>
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private WebServiceMsgService webServiceMsgService;

    @Autowired
    private WebServiceNotifyService webServiceNotifyService;

    /**
     * 配置访问根路径
     * 未设置默认为 http://localhost:8080/services
     * 自定义设置： http://localhost:8080/myService
     */
    @SuppressWarnings("all")
    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/myService/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean(name = "webServiceDemoEndPoint")
    public Endpoint webServiceDemoEndPoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus());
        //配置webservice服务接口 接口地址，对应的接口
        Endpoint.publish("/msgService",webServiceMsgService);
        Endpoint.publish("/notifyService",webServiceNotifyService);
        return endpoint;
    }

}
