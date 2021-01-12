package cn.tugos.spring.schedule.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author qinming
 * @date 2021-01-12 20:20:49
 * <p> 无 </p>
 */
@Service
@Slf4j
public class AsyncTest {

    @Async
    public void test1(){
        log.info("AsyncTest1"+System.currentTimeMillis()+"");
    }

    @Async
    public void test2(){
        log.info("AsyncTest2"+System.currentTimeMillis()+"");
    }


    @Async
    public void test3(){
        log.info("AsyncTest3"+System.currentTimeMillis()+"");
    }

}
