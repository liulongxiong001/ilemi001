package com.lemi.service.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicServiceImpl {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

}
