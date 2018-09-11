package com.oop.api.reverseengineering.freemaker;

import freemarker.cache.TemplateLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class StringTemplateLoader implements TemplateLoader {
    private String templateContent;

    public StringTemplateLoader(String templateContent) {
        this.templateContent = templateContent;
        if (templateContent == null) {
            this.templateContent = "";
        }

    }

    public void closeTemplateSource(Object templateSource) throws IOException {
        ((StringReader)templateSource).close();
    }

    public Object findTemplateSource(String name) throws IOException {
        return new StringReader(this.templateContent);
    }

    public long getLastModified(Object templateSource) {
        return 0L;
    }

    public Reader getReader(Object templateSource, String encoding) throws IOException {
        return (Reader)templateSource;
    }
}
