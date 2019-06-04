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
        service.run("tb_content", "tb_content_category", "tb_item", "tb_item_cat", "tb_item_desc", "tb_item_param", "tb_item_param_item", "tb_order", "tb_order_item", "tb_order_shipping", "tb_user");
        applicationContext.close();
    }
}
