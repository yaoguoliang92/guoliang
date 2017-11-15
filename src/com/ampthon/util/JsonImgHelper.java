package com.ampthon.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class JsonImgHelper {
	
	/**
     * TODO:将byte数组以Base64方式编码为字符串
     * @param bytes 待编码的byte数组
     * @return 编码后的字符串
     * */
    public static String encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }
    
    /**
     * TODO:将以Base64方式编码的字符串解码为byte数组
     * @param encodeStr 待解码的字符串
     * @return 解码后的byte数组
     * @throws IOException 
     * */
    public static byte[] decode(String encodeStr) throws IOException{
        byte[] bt = null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        bt = decoder.decodeBuffer(encodeStr);
        return bt;
    }
    
    /**
     * TODO:将两个byte数组连接起来后，返回连接后的Byte数组
     * @param front 拼接后在前面的数组
     * @param after 拼接后在后面的数组
     * @return 拼接后的数组
     * */
    public static byte[] connectBytes(byte[] front, byte[] after){
        byte[] result = new byte[front.length + after.length];
        System.arraycopy(front, 0, result, 0, after.length);
        System.arraycopy(after, 0, result, front.length, after.length);
        return result;
    }
    
    /**
     * TODO:将图片以Base64方式编码为字符串
     * @param imgUrl 图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
     * @return 编码后的字符串
     * @throws IOException 
     * */
    public static String encodeImage(String imgUrl) throws IOException{
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }
    /**
	 * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @author hongtou
	 * @Date 2016-05-30
	 * @param path 图片路径
	 * @return
	 */
	public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        InputStream in = new FileInputStream(path);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
    
}
