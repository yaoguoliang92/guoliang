package com.ampthon.util;

import java.util.LinkedHashMap;
import java.util.Map;


public enum DateConvertType {
	TOLONG("00", ""), //
	TOSTRING_CN_COMPAT_YMD("10", "yyyy年MM月dd日"), //
	TOSTRING_CN_COMMON_YMD("11", "yyyy年MM月dd日 HH时mm分ss秒"), 
	TOSTRING_CN_SLASH_YMD("12", "yyyy年MM月dd日"),
	TOSTRING_EN_COMPAT_YMD("20", "yyyyMMdd"), 
	TOSTRING_EN_COMMON_YMD("21", "yyyy-MM-dd"), 
	TOSTRING_EN_SLASH_YMD(	"22", "yyyy/MM/dd"),
	TOSTRING_EN_COMMON_YMDHMS("51", "yyyy-MM-dd HH:mm:ss");

	String type;
	String expression;

	DateConvertType(String type, String expression)
	{
		this.type = type;
		this.expression = expression;
	}

	public String getType()
	{
		return type;
	}

	public String getExpression()
	{
		return this.expression;
	}

	public Enum<?> getDefault()
	{
		return TOLONG;
	}

	public String getName()
	{
		return name();
	}

	static Map<String, DateConvertType> dateConvertTypeCache;

	static
	{
		DateConvertType[] dateConvertTypes = DateConvertType.values();
		dateConvertTypeCache = new LinkedHashMap<String, DateConvertType>(dateConvertTypes.length);
		for (DateConvertType dateConvertType : dateConvertTypes)
		{
			dateConvertTypeCache.put(dateConvertType.type, dateConvertType);
		}
	}

	public static DateConvertType getDateConvertType(String type)
	{
		DateConvertType ret = dateConvertTypeCache.get(type);
		return ret == null ? TOLONG : ret;
	}

}
