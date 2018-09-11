package com.oop.api.reverseengineering.core.file;

import lombok.extern.slf4j.Slf4j;
import com.oop.api.reverseengineering.core.config.GenerateConfig;
import com.oop.api.reverseengineering.core.constant.Constants;
import com.oop.api.reverseengineering.freemaker.FreeMarkerUtils;
import com.oop.api.reverseengineering.util.FileUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileBuilder {
    //java文件相对目录
    public static String coommonJavaPath = "/src/main/java/com/";
    //资源文件相对目录
    public static String coommonResourcesPath = "/src/main/resources/";
    /**sqlMap 生成的文件路径*/
    public static  String SQLMAP_FILE_PATH = "SQLMAP_FILE_PATH";
    /**PO 生成的文件路径*/
    public static  String PO_FILE_PATH = "PO_FILE_PATH";

    public static Map<String,String> filePathMap = new HashMap<>();
    /**相对于在项目跟路径下文件夹*/
    public static  String TEMPLATE_ROOT = "/template/";
    /**SQLMAP 模板数据*/
    public static  String TEMPLATE_SQL = "SqlMap.ftl";
    /**obj 模板数据*/
    public static  String TEMPLATE_PO = "Po.ftl";


    public static String comDir = "/com/";
    /**斜杠*/
    public static String SLASH = "/";

    public static String DOT=".";

    private GenerateConfig config;

    public FileBuilder(GenerateConfig config) {
        this.config = config;
        initFilePath();
    }

    private void initFilePath(){
        //项目子系统
        if(!StringUtils.isEmpty(config.getSystemName())){
            String interfaceProjectRoot = config.getWorkspacesPath() + config.getSystemName() + coommonJavaPath + config.getPackageName() + Constants.Separator.SLASH;
            //sqlmap文件路径
            filePathMap.put(SQLMAP_FILE_PATH, config.getWorkspacesPath() + config.getSystemName() + coommonResourcesPath + "mybatis/mapper/" + config.getPoName() + Constants.FileType.XML_SUFFIX);
            //PO文件路径
            filePathMap.put(PO_FILE_PATH,  interfaceProjectRoot +"po"+Constants.Separator.SLASH+ config.getPoName() + Constants.FileType.JAVA_SUFFIX);
        }
    }

    public boolean createPo(){
        if(filePathMap.containsKey(PO_FILE_PATH)){
            if(config != null && config.getParamMaps() != null && config.getParamMaps().size() > 0){
                String filePath = filePathMap.get(PO_FILE_PATH);
                String template = FileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_PO);
                Map<String, Object> paramsMap = config.getParamMaps();
                paramsMap.put("packagePath", filePath.substring(filePath.indexOf(comDir)+1, filePath.lastIndexOf(SLASH)).replaceAll(SLASH, DOT));
                String sqlMapContext = null;
                try {
                    sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                boolean status = FileUtils.writeFile(filePath, sqlMapContext);
                if(status){
                    System.out.println("创建PO文件：【成功】"+filePath);
                }else{
                    System.out.println("创建PO文件：【失败】"+filePath);
                }
            }else{
                System.out.println("生成PO异常");
            }
        }
        return true;
    }

    public boolean createMapper(){
        if(filePathMap.containsKey(SQLMAP_FILE_PATH)){
            if(config != null && config.getParamMaps().size() > 0){
                String template = FileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_SQL);
                template = template.replaceAll("#\\{", "00000\\{");
                template = template.replaceAll("%%\\{", "99999\\{");
                String sqlMapContext = null;
                try {
                    sqlMapContext = FreeMarkerUtils.getResult(template, config.getParamMaps());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sqlMapContext = sqlMapContext.replaceAll("00000\\{", "#\\{");
                sqlMapContext = sqlMapContext.replaceAll("99999\\{", "\\$\\{");
                String filePath = filePathMap.get(SQLMAP_FILE_PATH);
                boolean status = FileUtils.writeFile(filePath, sqlMapContext);
                if(status){
                    System.out.println("创建SQLMap文件：【成功】"+filePath);
                }else{
                    System.out.println("创建SQLMap文件：【失败】"+filePath);
                }
            }else{
                System.out.println("生成sqlmap异常");
            }
        }
        return true;
    }
}
