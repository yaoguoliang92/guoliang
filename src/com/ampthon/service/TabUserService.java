package com.ampthon.service;


import java.util.List;


import com.ampthon.pojo.Criteria;
import com.ampthon.pojo.TabUser;

public interface TabUserService {
    int countByExample(Criteria example);

    TabUser selectByPrimaryKey(String userid);

    List<TabUser> selectByExample(Criteria example);
    
    
    int selectPhoneNoCount(Criteria example);
    int deleteByPrimaryKey(int userid);

    int updateByPrimaryKeySelective(TabUser record);
    
    int insertSelective(TabUser record);

    int updateByPrimaryKey(TabUser record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(TabUser record, Criteria example);

    int updateByExample(TabUser record, Criteria example);

    
    String updateUserInfoSelective(TabUser record, Criteria example) throws Exception;
    
    String updateUserByPrimaryKey(TabUser record) throws Exception;
   
}