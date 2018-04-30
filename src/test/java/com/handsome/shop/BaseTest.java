package com.handsome.shop;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * by wangrongjun on 2018/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-dataSource.xml",
        "classpath:spring-main.xml",
})
@ActiveProfiles("test")
public class BaseTest {

}
