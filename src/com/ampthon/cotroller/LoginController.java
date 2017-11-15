package com.ampthon.cotroller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ampthon.dto.UserDto;
import com.ampthon.pojo.Criteria;
import com.ampthon.pojo.TabUser;
import com.ampthon.service.TabUserService;
import com.ampthon.util.Const;




/**
 * 
 * @author gl
 *  @deprecated  用户管理功能
 */
@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {

	@Autowired
	private TabUserService tabUserService;


	
	/**
	 *  登录
	 * @deprecated 输入用户名密码实现登录
	 * @param request 
	 * @return 用户信息
	 * @param data
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
    public @ResponseBody
    UserDto  getUserInfo(@RequestBody TabUser tabUser) {


		 Criteria example=new Criteria();
		 UserDto userDto =new UserDto();
		 
		 example.put("username", tabUser.getUsername());
		 example.put("password",tabUser.getPassword());
		 List<TabUser> tabUsers=	 tabUserService.selectByExample(example);
	
		if(null == tabUsers || 1 != tabUsers.size() )
		{
			userDto.setRtnFlag("0");
			userDto.setTabUser(tabUser);
		}
		else if(1 == tabUsers.size())
		{
			userDto.setRtnFlag("1");
			userDto.setTabUser(tabUsers.get(0));
		}
		
	
		 return userDto;
		
	  
 
    }

	/**
	 *  查询所有用户信息
	 * @deprecated 
	 * @param request 
	 * @return 用户信息
	 * @param 
	 */
	@RequestMapping(value = "/allUser")
    public @ResponseBody
    List<TabUser>  getUserInfoAll() {

		 Criteria example=new Criteria();
		
		 List<TabUser> tabUsers=	 tabUserService.selectByExample(example);
		 return tabUsers;
    }
	
	
	/**
	 *  新建用户
	 * @deprecated 
	 * @param request 
	 * @return 1成功 0失败
	 * @param data
	 */
	@RequestMapping(value = "/insert")
    public @ResponseBody
    int  insertUserInfo(@RequestBody TabUser tabUser) {

		 Criteria example=new Criteria();
		 UserDto userDto =new UserDto();
		 example.put("username", tabUser.getUsername().trim());
		 int count =	 tabUserService.countByExample(example);
		 if (0 == count) {
			 tabUserService.insertSelective(tabUser);
				 
			return 1;
		}else{
			return 0;
		}
		
    }
	
	
	
	/**
	 *  更新用户
	 * @deprecated 
	 * @param request 
	 * @return 1成功 0失败
	 * @param data
	 * @throws Exception
	 */
	@RequestMapping(value = "/update")
    public @ResponseBody
    int  updateUserInfo(@RequestBody TabUser tabUser) {

		 UserDto userDto =new UserDto();
		 tabUserService.updateByPrimaryKeySelective(tabUser);
		
		 return 1;
    }
	
	/**
	 *  更新用户
	 * @deprecated 
	 * @param request 
	 * @return 1成功 0失败
	 * @param data   
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
    public @ResponseBody
    int  deleteUserInfo(@RequestBody TabUser tabUser) {
		 
		 tabUserService.deleteByPrimaryKey(tabUser.getUserid());
	
		 return 1;
    }
}
