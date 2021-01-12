package cn.tugos.spring.schedule.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author qinming
 * @date 2021-01-12 20:15:12
 * <p> 无 </p>
 */
@Service
@Slf4j
public class ScheduleTest {

    @Autowired
    private AsyncTest asyncTest;

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduleTest1() {
        log.info("scheduleTest1" + System.currentTimeMillis());
        asyncTest.test1();
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduleTest2() {
        log.info("scheduleTest2" + System.currentTimeMillis());
        asyncTest.test2();
    }


    @Scheduled(cron = "0/5 * * * * *")
    public void scheduleTest3() {
        log.info("scheduleTest3" + System.currentTimeMillis());
        asyncTest.test3();
    }


}
