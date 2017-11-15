package com.ampthon.cotroller;



import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ampthon.util.PageData;
import com.ampthon.util.UtilUuid;




public class BaseController {
	
	protected Logger _log  = Logger.getLogger(this.getClass());
	

	
	protected ModelAndView mv = this.getModelAndView();
	
	protected PageData pd = new PageData();

	

	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		
		return new ModelAndView();
	}
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		mv.clear();
		PageData pds = new PageData();
		pds = new PageData(this.getRequest());
		
//		pds.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
		
		mv.addObject("pdm",pds);
		return pds;
	}
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UtilUuid.get32UUID();
	}
	

	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}


}
