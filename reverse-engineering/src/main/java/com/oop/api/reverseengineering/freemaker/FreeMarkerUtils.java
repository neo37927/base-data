package com.oop.api.reverseengineering.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerUtils {
    private Configuration cfg = new Configuration();
    private Template template = null;
    private static final String UTF8 = "UTF-8";

    public FreeMarkerUtils(String templateContent) {
        this.cfg.setTemplateLoader(new StringTemplateLoader(templateContent));
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setOutputEncoding("UTF-8");

        try {
            this.template = this.cfg.getTemplate("");
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public String getResult(Map<String, Object> paramMap) {
        StringWriter writer = new StringWriter();

        try {
            this.template.process(paramMap, writer);
        } catch (TemplateException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return writer.toString();
    }

    public String getResult(String jsonData) throws Exception {
        Template template = this.cfg.getTemplate("");
        Map<String, Object> root = new HashMap();
        root.put("json", jsonData);
        StringWriter writer = new StringWriter();
        template.process(root, writer);
        return writer.toString();
    }

    public String getResult(String key, String jsonData) throws Exception {
        Template template = this.cfg.getTemplate("");
        Map<String, Object> root = new HashMap();
        root.put(key, jsonData);
        StringWriter writer = new StringWriter();
        template.process(root, writer);
        return writer.toString();
    }

    public static String getResult(String template, Object obj) throws Exception {
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(new StringTemplateLoader(template));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputEncoding("UTF-8");

        try {
            Template templateobj = cfg.getTemplate("");
            templateobj.setEncoding("UTF-8");
            StringWriter writer = new StringWriter();
            templateobj.process(obj, writer);
            return writer.toString();
        } catch (IOException var5) {
            var5.printStackTrace();
            throw var5;
        }
    }

    public String getResultOfMap(Map<String, Object> root) throws Exception {
        StringWriter writer = new StringWriter();
        this.template.process(root, writer);
        return writer.toString();
    }
}
