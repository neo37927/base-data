package com.oop.api.reverseengineering.core.config;

import com.oop.api.reverseengineering.core.constant.Constants;
import com.entity.po.BasePo;
import com.oop.api.reverseengineering.core.entity.Column;
import com.oop.api.reverseengineering.core.util.GenerateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
public class GenerateConfig {
    private String tablePrefix;
    private String tableName;
    private String poName;
    private String firstLowerPoName;
    private String tableMiddle;
    private String workspacesPath;
    private String systemName;
    private String packageName;
    private String rootName;
    private Map<String,String> typeMap = new HashMap<>();
    /**包路径*/
    private List<String> importList = new ArrayList<>();
    /**字段列*/
    private List<Column> columnList  = new ArrayList<>();
    /**po定义的变量*/
    private List<Column> poColumnList  = new ArrayList<>();
    /**是否存在启用字段*/
    private String existDisabled = Constants.DeleteStatus.DISABLED.toString();
    /**是否存在UUID字段*/
    private String existUuid = Constants.DeleteStatus.DISABLED.toString();





    private Map<String,Object> paramMaps = new HashMap<>();
    private Map<String,String> excludeColumnMap = new HashMap<>();

    public GenerateConfig(String tableName,String systemName,String packageName,String rootName) throws Exception{
        this.tableName = tableName;
        this.systemName = systemName;
        this.packageName = packageName;
        this.rootName = rootName;
        init();
    }
    private void init() throws Exception{
        //提取表的前缀
        this.tablePrefix = this.tableName.substring(0, this.tableName.indexOf(Constants.Separator.UPDERLINE));
        this.poName = GenerateUtils.tableToPoName(tableName);
        this.tableMiddle = this.poName.toLowerCase().substring(tablePrefix.length());
        //首字母小写
        this.firstLowerPoName = this.poName.substring(0, 1).toLowerCase()+this.poName.substring(1);
        //初始化项目跟路径
        String path =  (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:", "").replaceAll("%20", " ").trim();
        //构建项目的名称为Parent,截取parent之前的工作空间目录jar-data
        path = path.substring(0, path.indexOf(rootName)+1);
        this.workspacesPath = path;
        initColumn();
        initMap();

        initExcludeColumn();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 初始化字段列
     * @throws Exception
     */
    private void initColumn() throws Exception {
        if(columnList == null || columnList.size() == 0){
            //初始化表中所有字段信息
            this.columnList = GenerateUtils.columnToList(tableName);

            for(Column column : this.columnList){
                typeMap.put(column.getType(), column.getType());
                if(column.getName().equals("disabled")){
                    existDisabled = Constants.DeleteStatus.ENABLED.toString();
                }else if(column.getName().equals("uuid")){
                    existUuid = Constants.DeleteStatus.ENABLED.toString();
                }

            }
        }
    }
    /**
     * 初始化 map参数，主要数据与模板合成
     */
    private void initMap(){
        paramMaps.put("poName", this.poName);
        paramMaps.put("firstLowerPoName", this.firstLowerPoName);
//        paramMaps.put("childSystemName", this.childSystemName);
//        paramMaps.put("childSystemAPIName", this.childSystemAPIName);
//        paramMaps.put("childSystemWeb", this.childSystemWeb);
        paramMaps.put("existDisabled", this.existDisabled);
        paramMaps.put("tableName", this.tableName);
        paramMaps.put("tablePrefix", this.tablePrefix.toLowerCase());
        paramMaps.put("tableMiddle", this.tableMiddle.toLowerCase());

        paramMaps.put("existUuid", this.existUuid);
        paramMaps.put("tableMiddle", this.tableMiddle);
        paramMaps.put("columnList", this.columnList);
        paramMaps.put("poColumnList", this.poColumnList);
        paramMaps.put("importList", this.importList);
    }
    /***
     * 初始化排除的列
     */
    private void initExcludeColumn(){
        Field[] fields = BasePo.class.getDeclaredFields();
        for (Field f : fields) {
            if(!f.getName().equals("serialVersionUID")){
                this.excludeColumnMap.put(f.getName(), f.getName());
            }
        }
        Map<String,String> importMap = new HashMap<>();
        for(Column column : this.columnList){
            if(!excludeColumnMap.containsKey(column.getName())){
                poColumnList.add(column);
                String importStr = GenerateUtils.dbTypeToImportPath(column.getType());
                if(!importMap.containsKey(importStr)){
                    importMap.put(importStr, null);
                    importList.add(importStr);
                }
            }
        }
    }
}
