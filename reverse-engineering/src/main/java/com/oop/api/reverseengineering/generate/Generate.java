package com.oop.api.reverseengineering.generate;

import com.oop.api.reverseengineering.core.file.FileBuilder;
import com.oop.api.reverseengineering.core.util.GenerateUtils;
import com.oop.api.reverseengineering.util.PropertiesUtils;
import com.oop.api.reverseengineering.core.config.GenerateConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆向工程，基于本项目生成
 */
public class Generate {
    public static void generate() throws Exception {

        PropertiesUtils p = new PropertiesUtils();
        List<String> configFilePath = new ArrayList<>();
        configFilePath.add("application.properties");
        p.setUrls(configFilePath);

        String systemName = p.get("generate.systemName");
        String packageName = p.get("generate.packageName");

        String generateAll = p.get("generate.all");
        String rootName = p.get("generate.root");
        List<String> list = new ArrayList<>();
        if ("1".endsWith(generateAll)) {
            // 全库生成
            list = GenerateUtils.getTable();
        } else {
            String tableNames = p.get("generate.tableNames");
            // 部分生成
            String[] tables = tableNames.split(",");
            for (String table : tables) {
                list.add(table);
            }
        }
        try {
            for (String tableName : list) {
                GenerateConfig config = new GenerateConfig(tableName,
                        systemName, packageName, rootName);
                FileBuilder builder = new FileBuilder(config);
                builder.createMapper();
                builder.createPo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Generate.generate();
    }
}
