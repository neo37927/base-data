package com.oop.api.reverseengineering.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

public class PropertiesUtils extends Properties {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, String> properties = new HashMap();
    private List<String> urls = new ArrayList();

    public PropertiesUtils() {
    }

    public String get(String key) {
        return (String)this.properties.get(key);
    }

    public String get(Enum key) {
        return (String)this.properties.get(key.name());
    }

    public List<String> getUrls() {
        return this.urls;
    }

    public void setUrls(List<String> urls) throws Exception {
        ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
        Iterator var3 = urls.iterator();

        while(var3.hasNext()) {
            String url = (String)var3.next();
            this.logger.debug("[<=配置文件:{}=>]", url.substring(url.lastIndexOf("/") + 1, url.length()));
            InputStream inputStream = currentThreadClassLoader.getResourceAsStream(url);
            if (inputStream == null) {
                inputStream = this.getClass().getResourceAsStream(url);
            }

            this.load(inputStream);
            Enumeration names = this.propertyNames();

            while(names.hasMoreElements()) {
                String name;
                this.properties.put(name = (String)names.nextElement(), this.getProperty(name));
                this.logger.debug("[{}:{}]", name, this.getProperty(name));
            }
        }

        this.urls = urls;
    }
}
