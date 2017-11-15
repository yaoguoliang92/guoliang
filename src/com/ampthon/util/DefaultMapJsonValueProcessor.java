package com.ampthon.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DefaultMapJsonValueProcessor implements JsonValueProcessor
{
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

	@SuppressWarnings("unchecked")
	private Object process(Object value)
	{
		Map<Object, Object> map = (Map<Object, Object>) value;
		if (map.isEmpty())
			return JSONArray.fromObject(Collections.emptyList());// JSONObject.fromObject(Collections.emptyList()).toString();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map.Entry<Object, Object> entry : map.entrySet())
		{
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("key", entry.getKey());
			tmpMap.put("value", entry.getValue());
			list.add(tmpMap);
		}
		return JSONArray.fromObject(list);// JSONObject.fromObject(list).toString();
	}

}
