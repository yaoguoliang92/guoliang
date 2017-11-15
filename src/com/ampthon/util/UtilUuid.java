package com.ampthon.util;

import java.util.UUID;
/*
 * @author hongtou
 */
public class UtilUuid {
    /**
     * @author hongtou
     *@return 获取uuid
     * @param fileName    文件的名
     */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
 
  
}