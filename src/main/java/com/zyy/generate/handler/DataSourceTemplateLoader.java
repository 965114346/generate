package com.zyy.generate.handler;

import freemarker.cache.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;

/**
 * @author yangyang.zhang
 * @date 2019/5/29 21:19
 */
@Component
public class DataSourceTemplateLoader implements TemplateLoader {

    @Override
    public Object findTemplateSource(String name) throws IOException {
        return null;
    }

    @Override
    public long getLastModified(Object templateSource) {
        return 0;
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        return null;
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {

    }
}
