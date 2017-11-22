package com.lemi.dao;

import org.springframework.stereotype.Repository;

import com.lemi.model.WeixinUserInfo;

@Repository
public interface WeixinUserInfoMapper {
    int deleteByPrimaryKey(String openid);

    int insert(WeixinUserInfo record);

    int insertSelective(WeixinUserInfo record);

    WeixinUserInfo selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(WeixinUserInfo record);

    int updateByPrimaryKey(WeixinUserInfo record);
}