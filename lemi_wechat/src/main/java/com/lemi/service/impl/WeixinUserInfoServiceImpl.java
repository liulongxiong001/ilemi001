package com.lemi.service.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemi.dao.WeixinUserInfoMapper;
import com.lemi.model.WeixinUserInfo;
import com.lemi.service.WeixinUserInfoServiceI;

@Service
public class WeixinUserInfoServiceImpl extends BasicServiceImpl implements WeixinUserInfoServiceI{

	@Autowired
	private WeixinUserInfoMapper weixinUserInfoMapper;
	
	@Override
	public WeixinUserInfo selectById(String openid) {
		return weixinUserInfoMapper.selectByPrimaryKey(openid);
	}

	@Override
	public int insert(WeixinUserInfo userInfo) {
		return weixinUserInfoMapper.insert(userInfo);
	}

	@Override
	public int update(WeixinUserInfo userInfo) {
		return weixinUserInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public int delete(String openid) {
		return weixinUserInfoMapper.deleteByPrimaryKey(openid);
	}

}
