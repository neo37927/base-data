package com.oop.api.jardatamybatissharding.constant;


/**
 * 常量数据类
 */
public class Constants {


    /**
     * 返回标识
     */
    public interface ResultStatus {
        /**
         * 成功
         */
        String RESULT_SUCCESS = "SUCCESS";
        /**
         * 系统错误
         */
        String RESULT_SYSTEM_ERROR = "SYSTEM_ERROR";
        /**
         * 远程调用失败
         */
        String REMOTE_CALL_FAILED = "REMOTE_CALL_FAILED";
        /**
         * 结果信息不存在
         */
        String RESULT_INFO_NOT_FOUNT="RESULT_INFO_NOTFOUND";
        /**
         * 参数格式不符合要求,请检查参数列表
         */
        String VALID_ERROR="VALID_ERROR";
    }


    /**
     * java包装类型的扩展常量
     */
    public interface WrapperExtend {

        Integer ZERO = 0;
        String EMPTY = "";

    }

    /**
     * 分隔符
     */
    public interface Separator {
        /**
         * 表名后缀
         */
        String TABLE_SUFFIX = "_T";
        /**
         * 分隔符'_'
         */
        String UPDERLINE = "_";
        /**
         * 逗号
         */
        String COMMA = ",";
        /**
         * 反斜杠
         */
        String BACKSLASH = "\\";
        /**
         * 斜杠
         */
        String SLASH = "/";
        /**
         * 点
         */
        String DOT = ".";
        /**
         * 回车换行
         */
        String CRLF = "\r\n";
        /**
         * 单引号
         */
        String SINGLE_QUOTES = "'";
        /**
         * 双引号
         */
        String SINGLE_DOUBLEQUOTES = "\"";
        /**
         * 叹号
         */
        String EXCLAMATION_MARK = "!";
        /**
         * 空格
         */
        String BLANK = " ";
        /**
         * 百分号
         */
        String PERCENT = "%";
        /**
         * 两位小数
         */
        String TWO_DIGITS_DECIMALS = "#.00";
        /**
         * 连接符
         */
        String CONNECTOR = "-";
        /**
         * 顿号
         */
        String PAUSE = "、";
        /**
         * 左括号
         */
        String LBRACKET = "(";
        /**
         * 右括号
         */
        String RBRACKET = ")";
        /**
         * 冒号
         */
        String COLON = ":";
        /**
         * 问号
         */
        String QUESTION_MARK = "?";
        /**
         * 等号
         */
        String EQUAL_MARK="=";
        /**
         * 连接符号
         */
        String AND_MARK="&";

        /**
         * 换行符
         */
        public static String LINE_SEPARATOR = java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("line.separator"));
    }

    /**
     * 文件后缀类型
     */
    public interface FileType {
        /**
         * xml后缀
         */
        String XML_SUFFIX = ".xml";
        /**
         * java后缀
         */
        String JAVA_SUFFIX = ".java";
        /**
         * jsp后缀
         */
        String JSP_SUFFIX = ".jsp";
    }

    public interface PageParams {
        /**
         * 页面列表数据的累计总和
         */
        String START = "start";
        /**
         * 页面请求页数
         */
        String DRAW = "draw";
        /**
         * 请求的页数
         */
        String PAGE_NO = "pageNo";
        /**
         * 每页显示的变量
         */
        String LENGTH = "length";
        /**
         * 页面起始页
         */
        int PAGE_START = 1;
        /**
         * 模糊检索参数后缀
         */
        String FUZZY_SEARCH_PARAM_SUFFIX = "Like";
        /**
         * 或查询前缀
         */
        String OR = "OR";
    }

    /**
     * API调用返回状态
     */
    public interface ApiCall {
        int API_CALL_SUCCESS = 0;
        int API_CALL_SYS_ERROR = 500;
        int API_CALL_NOT_FOUND = 404;
    }

    /**
     * 是否删除
     */
    public interface DeleteStatus {
        //所有禁用为1
        Integer DISABLED = 1;
        //所有启用为0
        Integer ENABLED = 0;
    }

    public interface SQLConstants {
        String ORDER_BY_TYPE = "orderByType";
        String ORDER_COLUMN = "orderByColumn";
        String ASC = "asc";
        String DESC = "desc";
        /**
         * 模糊检索参数后缀
         */
        String FUZZY_SEARCH_PARAM_SUFFIX = "Like";
        String PAGE_NO = "pageNo";
        String PAGE_SIZE = "pageSize";

        String CUSTOMER_SQL = "CustomerSQL";

        String DATABASE_KEY="FINANCE_DATA";

    }

    public interface CacheBuff{
        String TABLE = "TABLE";
        String ROW_CACHE ="ROW_CACHE";
        String TABLE_CACHE="TABLE_CACHE";
        String DATABASE_CACHE="DATABASE_CACHE";

    }

    public interface Charset{
        String UTF8="UTF-8";
        String GBK ="GBK";
    }
}
