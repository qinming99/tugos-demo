package cn.tugos.spring.local.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author qinming
 * @date 2021-02-18 17:31:04
 * <p> 无 </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CacheTestServiceTest {

    @Autowired
    private CacheTestService cacheTestService;

    @Test
    public void testTestGetName() throws Exception {
        cacheTestService.getName(1);
        cacheTestService.getAge(2);

        TimeUnit.SECONDS.sleep(10);

        cacheTestService.getName(1);
        cacheTestService.getAge(2);
    }
}
