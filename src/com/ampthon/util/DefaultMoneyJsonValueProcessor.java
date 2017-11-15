package com.ampthon.util;

import java.text.NumberFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DefaultMoneyJsonValueProcessor implements JsonValueProcessor {
	private NumberFormat number;

	public DefaultMoneyJsonValueProcessor() {
		super();
		number = NumberFormat.getNumberInstance();
		number.setMinimumFractionDigits(2);
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		return process(value);
	}

	private Object process(Object value) {
		if (null != value) {
			return number.format(value);
		}
		return "0";
	}
}