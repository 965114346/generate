package com.zyy.generate;

import com.zyy.generate.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 代码生成入口
 * @author yangyang.zhang
 */
@Slf4j
public class GenerateApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/application.xml");
        GenerateService service = applicationContext.getBean(GenerateService.class);
        service.run("t_blog");
        applicationContext.close();
    }
}
