package com.ampthon.mapper;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.ampthon.pojo.Criteria;
import com.ampthon.pojo.TabUser;

public interface TabUserMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(int userid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TabUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TabUser record);

    /**
     * 根据条件查询记录集
     */
    List<TabUser> selectByExample(Criteria example);

    List<Map<String, Object>> selectUserInfo(Criteria example);
    
    List<Map<String, Object>> selectUserListByKesInTotal(Criteria example);
    int selectUserListByKesInTotalCount(Criteria example);
    List<Map<String, Object>> selectUserListByKesInMonth(Criteria example);
    int selectUserListByKesInMonthCount(Criteria example);
    List<Map<String, Object>> selectUserListByKesInQuerter(Criteria example);
    int selectUserListByKesInQuerterCount(Criteria example);
    List<Map<String, Object>> selectUserDetailandAddr(Criteria example);
    List<Map<String, Object>> selectLastMonthAndLastQuarter(Criteria example);
    
    String updateLevelProcedureByUserID(Criteria example);
    String updateLevelProcedure(Criteria example);
  
    /**
     * 根据主键查询记录
     */
    TabUser selectByPrimaryKey(String userid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") TabUser record, @Param("condition") Map<String, Object> condition);

    
    
    /**
     * 根据条件更新
     */
    int updateUserInfoSelective(@Param("record") TabUser record, @Param("condition") Map<String, Object> condition);
    
    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") TabUser record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TabUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TabUser record);
    
    List<Map<String, Object>> selectPhoneNo(Criteria example);
    int selectPhoneNoCount(Criteria example);
    
    int selectByMapCount(Criteria example);
    List<Map<String, Object>> selectByMap(Criteria example);
    
    
}