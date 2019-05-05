package com.zyy.generate.dao;

import com.zyy.generate.pojo.Column;
import com.zyy.generate.pojo.Table;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application.xml")
public class GenerateDaoImplTest {

    @Autowired
    private GenerateMapper dao;

    @Test
    public void queryBeanField() {
        List<Column> tBlog = dao.queryColumns("t_blog");
        log.info("{}", tBlog.get(0));
    }

    @Test
    public void queryTable() {
        Table table = dao.queryTable("t_blog");
        log.info("{}", table);
    }
}