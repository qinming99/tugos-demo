package cn.tugos.nacos.dubbo.consumer;

import com.tugos.nacos.dubbo.api.IHelloDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableDubbo
@EnableScheduling
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}


	@Reference
	private IHelloDubboService iHelloDubboService;


	@Scheduled(cron = "0/10 * * * * *")
	public void test(){
		iHelloDubboService.say("你好Dubbo!!!");
	}

}
