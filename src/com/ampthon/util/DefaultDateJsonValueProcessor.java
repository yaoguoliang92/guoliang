package com.ampthon.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DefaultDateJsonValueProcessor implements JsonValueProcessor
{
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public DefaultDateJsonValueProcessor(DateConvertType dateConvertType)
	{
		try
		{
			dateFormat = new SimpleDateFormat(dateConvertType.getExpression());
		} catch (Exception ex)
		{
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1)
	{
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2)
	{
		return process(value);
	}

	private Object process(Object value)
	{
		if(null!=value){
			return dateFormat.format((Date) value);
		}
		return "";
	}
}

