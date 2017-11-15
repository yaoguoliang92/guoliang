package com.ampthon.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
/*
 * @author hongtou
 */
public class MD5 {
	
	/*
	 * md5加密
	 */
	public static String md5(String str) 
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) 
			{
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return str;
	}
	

	
	/**
	 * 读取文件的md5值
	 * @deprecated 传入base64的字符串返回图片的md5值
	 * @param request base64
	 * @param response md5值
	 * @param data
	 * @throws Exception
	 */
    public static String getFileMD5ByBase64(String base64) throws Exception 
    {  
        String value = null;  
        InputStream inputStream = null;
   	 	MessageDigest md5=null;
   	 	try 
   	 	{  
    	
	    	byte[] base64arr=JsonImgHelper.decode(base64);
	    	 byte[] buffer = new byte[8192];
	    	inputStream=byte2Input(base64arr);
	    	md5 = MessageDigest.getInstance("MD5");
	        int len;
	    	while((len = inputStream.read(buffer)) != -1)
	    	{
	             md5.update(buffer, 0, len);
	        }
	    	
		    	 
		} 
   	 	catch (Exception e) 
   	 	{  
	        e.printStackTrace();  
	    }
   	 	finally 
	    {  
	            if(null != inputStream) 
	            {  
	            try {  
	                inputStream.close();
	            } 
	            catch (IOException e) 
	            {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	    return DigestUtils.md5Hex(md5.digest()).substring(8, 24);
    } 
    
	/**
	 * byte[] 转InputStream流
	 * @deprecated 
	 * @param request byte[]
	 * @param response InputStream
	 * @param data
	 * @throws Exception
	 */
    public static final InputStream byte2Input(byte[] buf) {  
        return new ByteArrayInputStream(buf);  
    }  
    
    
   
//    /**
//	 * 计算MD5方法
//	 * @deprecated 
//	 * @param request byte[]
//	 * @param response String
//	 * @param data
//	 * @throws Exception
//	 */
//    private static char[] hexChar = 
//    	{
//    	'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'	
//    	};
//     protected static String toHexString(byte[] b){
//         StringBuilder sb = new StringBuilder(b.length*2);
//         for(int i=0;i<b.length;i++){
//              sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
//             sb.append(hexChar[b[i] & 0x0f]);
//         }
//         return sb.toString();
//     }
  
}
