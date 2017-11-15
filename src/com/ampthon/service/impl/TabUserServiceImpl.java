package com.ampthon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampthon.mapper.TabUserMapper;
import com.ampthon.pojo.Criteria;
import com.ampthon.pojo.TabUser;
import com.ampthon.service.TabUserService;
import com.ampthon.util.Const;


@Service
public class TabUserServiceImpl implements TabUserService {
	@Autowired
	private TabUserMapper tabUserMapper;

	

	private static final Logger logger = LoggerFactory
			.getLogger(TabUserServiceImpl.class);

	public int countByExample(Criteria example) {
		int count = this.tabUserMapper.countByExample(example);
		logger.debug("count: {}", count);
		return count;
	}

	public TabUser selectByPrimaryKey(String userid) {
		return this.tabUserMapper.selectByPrimaryKey(userid);
	}

	public List<TabUser> selectByExample(Criteria example) {
		return this.tabUserMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(int userid) {
		return this.tabUserMapper.deleteByPrimaryKey(userid);
	}

	public int updateByPrimaryKeySelective(TabUser record) {
		return this.tabUserMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TabUser record) {
		return this.tabUserMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(Criteria example) {
		return this.tabUserMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(TabUser record, Criteria example) {
		return this.tabUserMapper.updateByExampleSelective(record,
				example.getCondition());
	}

	public int updateByExample(TabUser record, Criteria example) {
		return this.tabUserMapper.updateByExample(record,
				example.getCondition());
	}

	public int insert(TabUser record) {
		return this.tabUserMapper.insert(record);
	}

	public int insertSelective(TabUser record) {
		return this.tabUserMapper.insertSelective(record);
	}

	

	@Override
	public int selectPhoneNoCount(Criteria example) {
		// TODO Auto-generated method stub
		return this.tabUserMapper.selectPhoneNoCount(example);
	}

	@Override
	public String updateUserByPrimaryKey(TabUser record) throws Exception {
		// TODO Auto-generated method stub

		tabUserMapper.updateByPrimaryKeySelective(record);
		return Const.RTN_SUCESSS;
	}

	@Override
	public String updateUserInfoSelective(TabUser record, Criteria example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}