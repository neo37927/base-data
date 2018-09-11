package com.oop.api.reverseengineering.core.constant;

import sun.security.action.GetPropertyAction;

import java.security.AccessController;

public class Constants {
    public interface DeleteStatus {
        Integer DISABLED = 1;
        Integer ENABLED = 0;
    }

    public interface Separator {
        String TABLE_SUFFIX = "_T";
        String UPDERLINE = "_";
        String COMMA = ",";
        String BACKSLASH = "\\";
        String SLASH = "/";
        String DOT = ".";
        String CRLF = "\r\n";
        String SINGLE_QUOTES = "'";
        String SINGLE_DOUBLEQUOTES = "\"";
        String EXCLAMATION_MARK = "!";
        String BLANK = " ";
        String PERCENT = "%";
        String TWO_DIGITS_DECIMALS = "#.00";
        String CONNECTOR = "-";
        String PAUSE = "、";
        String LBRACKET = "(";
        String RBRACKET = ")";
        String COLON = ":";
        String QUESTION_MARK = "?";
        String EQUAL_MARK = "=";
        String AND_MARK = "&";
        String LINE_SEPARATOR = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));
    }

    public interface FileType {
        String XML_SUFFIX = ".xml";
        String JAVA_SUFFIX = ".java";
        String JSP_SUFFIX = ".jsp";
    }
}
