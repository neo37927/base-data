package com.oop.api.jardatamybatissharding.util;



import com.oop.api.jardatamybatissharding.constant.Constants;

import java.util.Objects;

public class StringDefaultValue {

	private static String ZERO = "0";

	public static String StringValue(Object value) {
		return Objects.toString(value, "");
	}

	public static int intValue(Object value) {
		String valueStr = Objects.toString("".equals(value) ? ZERO : value, ZERO);
		return Integer.valueOf(valueStr.contains(Constants.Separator.DOT)
				? valueStr.substring(0, Objects.toString(value, ZERO).indexOf(Constants.Separator.DOT)) : valueStr);
	}

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
