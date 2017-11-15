package com.ampthon.util;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import sun.misc.BASE64Encoder;


import net.sf.ezmorph.MorpherRegistry;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

public class JsonHelper {
	/** 页面传至后台时，json数据在request的参数名称 */
	/*
	 * public final static String JSON_ATTRIBUTE = "json"; public final static
	 * String JSON_ATTRIBUTE1 = "json1"; public final static String
	 * JSON_ATTRIBUTE2 = "json2"; public final static String JSON_ATTRIBUTE3 =
	 * "json3"; public final static String JSON_ATTRIBUTE4 = "json4";
	 */
	private static JsonConfig jsonConfig = null;
	private static Map<String, Object> mapv;


	static {
		jsonConfig = configJson(DateConvertType.TOSTRING_EN_COMMON_YMD);
		setDataFormat2JAVA();
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object getDTO(String jsonString, Class<?> clazz) {
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
		morpherRegistry.registerMorpher(new DateMorpherEx(new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"},(Date)null));
		return JSONObject.toBean(jsonObject,clazz);
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 *            集合属性的类型 (key : 集合属性名, value : 集合属性类型 class) eg: ("beansList" :
	 *            Bean.class)
	 * @return
	 */
	public static Object getDTO(String jsonString, Class<?> clazz, Map<?, ?> map) {
		JSONObject jsonObject = null;
		try {
			// setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class<?> clazz) {
		// setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class<?> clazz, Map<?, ?> map) {
		// setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static List<Object> getDTOList(String jsonString, Class<?> clazz) {
		// setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List<Object> list = new ArrayList<Object>();
		for (Iterator<?> iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 *            集合属性的类型
	 * @return
	 */
	public static List<Object> getDTOList(String jsonString, Class<?> clazz, Map<?, ?> map) {
		// setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List<Object> list = new ArrayList<Object>();
		for (Iterator<?> iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * 从 json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"}
	 * 注意commons-collections版本，必须包含
	 * org.apache.commons.collections.map.MultiKeyMap
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> getMapFromJson(String jsonString) {
		// setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
			String key =  (String)iter.next();
			map.put(key.trim(), jsonObject.get(key));
		}
		return map;
	}

	/**
	 * 从 json数组中得到相应java数组 json 形如：["123", "456"]
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayFromJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 把数据对象转换成json字符串 DTO 对象形如：{"id" : idValue, "name" : nameValue, ...}
	 * 数组对象形如：[{}, {}, {}, ...] map对象形如： {key1 : {"id" : idValue, "name" :
	 * nameValue, ...}, key2 : {}, ...}
	 * 
	 * @param object
	 * @return
	 */
	public static String getJSONString(Object object,String[] excluds) {// throws Exception{
		String jsonString = null;
		// 日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DefaultDateJsonValueProcessor(DateConvertType.TOSTRING_EN_COMMON_YMD));
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DefaultDateJsonValueProcessor(DateConvertType.TOSTRING_EN_COMMON_YMD));
		jsonConfig.registerJsonValueProcessor(BigDecimal.class, new DefaultMoneyJsonValueProcessor());
		if(excluds!=null){
			jsonConfig.setExcludes(excluds);
		}
		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			} else {
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}

	public static String getJSONCustomizeString(Object object, boolean exclude, String... fields) {// throws
																									// Exception{
																									// String
																									// jsonString
																									// =
																									// null;
		if (object != null) {
			Map<String, Object> map = getFieldValues(object, exclude, fields);
			return JSONObject.fromObject(map, jsonConfig).toString();
		}
		return "{}";
		// return jsonString == null ? "{}" : jsonString;
	}

	public static JsonConfig configJson(DateConvertType dataConvertType, String... excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class, new DefaultDateJsonValueProcessor(dataConvertType));
		jsonConfig.registerJsonValueProcessor(Map.class, new DefaultMapJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(HashMap.class, new DefaultMapJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(LinkedHashMap.class, new DefaultMapJsonValueProcessor());
		return jsonConfig;
	}

	private static void setDataFormat2JAVA() {
		// 设定日期转换格式
		// JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new
		// String[] {"yyyy/MM/dd","yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// StringToDateMorpher());
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// DoubleToDateMorpher());
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// StringToParamBeanMorpher());
	}

	public static String getResultData(String message, String url, Exception exception) {
		JSONObject object = new JSONObject();
		object.put("message", message);
		object.put("url", url);
		object.put("exception", exception);
		return object.toString();
	}

	/*
	 * public static String getPageData(TailPage page) { return
	 * getPageData(page, false); }
	 */

	/**
	 * 创建分页所需要的json数据
	 * 
	 * @param page
	 * @return
	 */
	/*
	 * public static String getPageData(TailPage page, boolean withItem) {
	 * JSONObject object = new JSONObject(); object.put("pageNumber",
	 * page.getPageNumber()); object.put("pageSize", page.getPageSize());
	 * object.put("lastPageNumber", page.getLastPageNumber());
	 * object.put("totalItemsCount", page.getTotalItemsCount()); if (withItem)
	 * object.put("items", page.getItems()); return object.toString(); }
	 */

	@SuppressWarnings("rawtypes")
	public static String getListData(List<Map> list) {
		JSONObject object = new JSONObject();
		object.put("items", list);
		return object.toString();
	}

	public static List<Map<String, Object>> filterListData(List<Object> list, boolean exclude, String... fieldNames) {
		List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
		for (Object o : list) {
			item.add(getFieldValues(o, exclude, fieldNames));
		}
		return item;
	}

	// public static Map<String, Object> filterObjectData(Object bean,
	// DateConvertType dateConvertType, boolean exclude,
	// String... fieldNames) {
	// Map<String, Object> fieldMap = getFieldValues(bean, exclude, fieldNames);
	// for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
	// String key = entry.getKey();
	// Object obj = entry.getValue();
	// if (obj instanceof Date) {
	// Date date = (Date) obj;
	// switch (dateConvertType) {
	// case TOLONG:
	// fieldMap.put(key, date.getTime());
	// break;
	// default:
	// fieldMap.put(
	// key,
	// DateTimeHelper.getDateTime(date,
	// dateConvertType.getExpression()));
	// break;
	// }
	// }
	// }
	// return fieldMap;
	// }

	// @Deprecated
	public static Map<String, Object> filterObjectData(boolean convertDateToLong, Object bean, boolean exclude, String... fieldNames) {
		Map<String, Object> map = getFieldValues(bean, exclude, fieldNames);
		if (convertDateToLong) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object obj = entry.getValue();
				if (obj instanceof Date) {
					obj = ((Date) obj).getTime();
				}
				map.put(key, obj);
			}
		}
		return map;
	}

	/**
	 * 将map转化为json数据结构，并且自动将Date类型转化为 long型
	 * 
	 * @param map
	 * @return
	 */
	public static String getContentData(Map<String, Object> map) {
		return getContentData(true, map);
	}

	/**
	 * 将map转化为json数据结构
	 * 
	 * @param map
	 *            待转化的map
	 * @return
	 */
	public static String getContentData(boolean convertDateToLong, Map<String, Object> map) {
		JSONObject json = new JSONObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object obj = entry.getValue();
			if (convertDateToLong && obj instanceof Date) {
				obj = ((Date) obj).getTime();
			}
			json.put(key, obj);
		}
		return json.toString();
	}

	/**
	 * 将一个bean中指定属性之外的全部属性，转化为json数据
	 * 
	 * @param bean
	 * @param fieldNames
	 * @return
	 */
	public static String getObjectData(Object bean, String... fieldNames) {
		return getObjectData(bean, true, fieldNames);
	}

	/**
	 * 将一个bean中指定的属性，转化为json数据
	 * 
	 * @param bean
	 * @param exclude
	 *            true 不包括; false 包括
	 * @param fieldNames
	 * @return
	 */
	public static String getObjectData(Object bean, boolean exclude, String... fieldNames) {
		return getObjectData(true, bean, exclude, fieldNames);
	}

	/**
	 * 将一个bean中指定的属性，转化为json数据
	 * 
	 * @param convertDateToLong
	 * @param bean
	 * @param exclude
	 *            true 不包括; false 包括
	 * @param fieldNames
	 * @return
	 */
	public static String getObjectData(boolean convertDateToLong, Object bean, boolean exclude, String... fieldNames) {
		Map<String, Object> map = getFieldValues(bean, exclude, fieldNames);
		return getContentData(convertDateToLong, map);
	}

	public static Map<String, Object> getFieldValues(Object bean, boolean exclude, String[] fieldNames) {
		if (bean == null)
			return Collections.emptyMap();
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);
		Set<String> fields;
		if (ArrayUtils.isEmpty(fieldNames))
			fields = new LinkedHashSet<String>();
		else {
			fields = new LinkedHashSet<String>(Arrays.asList(fieldNames));
		}
		Map<String, Object> ret = new LinkedHashMap<String, Object>();
		PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
		for (PropertyDescriptor pd : descriptors) {
			if (pd.getReadMethod() == null) {
				continue;
			}
			String name = pd.getName();
			if (exclude) {
				if (!(fields.contains(name)))
					;
			} else {
				if (!(fields.contains(name))) {
					continue;
				}
				ret.put(name, beanWrapper.getPropertyValue(name));
			}
		}
		return ret;
	}
	public static String getValueByKey(String jsonString,String keyString)
	{
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.getString(keyString);
	}
	public static Map<String, Object> getArgsFromRequest(String jsonString)
	{
//		JSONObject jsonObject = null;
		String[] ag=null;
		
		String[] value=null;
		 mapv = new HashMap<String, Object>();
		try {

			ag=jsonString.split("&");
			for(int arrj=0;arrj<ag.length;arrj++)
			{
				value=ag[arrj].split("=");
				
				mapv.put(value[0], value[1]);
					
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapv;
	}
	
	public static Map<String, Object> getImageInfoFromRequest(String jsonString,String[] keyString)
	{
		JSONObject jsonObject = null;
		 mapv = new HashMap<String, Object>();
		try {
			jsonObject = JSONObject.fromObject(jsonString);
			for(int arri=0;arri<keyString.length;arri++)
			{
				mapv.put(keyString[arri], jsonObject.getString(keyString[arri]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapv;
	}
	public static Map<String, Object> getJsonFromRequest(String jsonString,String[] keyString)
	{
		JSONObject jsonObject = null;
		 mapv = new HashMap<String, Object>();
		try {
			jsonObject = JSONObject.fromObject(jsonString);
			for(int arri=0;arri<keyString.length;arri++)
			{
				mapv.put(keyString[arri], jsonObject.getString(keyString[arri]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapv;
	}
	
	
}
