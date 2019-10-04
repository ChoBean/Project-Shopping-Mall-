package com.myproject.shop.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class AbstractDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Object insert(String queryId, Object params){
		  return sqlSession.insert(queryId, params);
	  }
	      
	public Object update(String queryId, Object params){
		return sqlSession.update(queryId, params);
	}
	      
	public Object delete(String queryId, Object params){
		return sqlSession.delete(queryId, params);
	}
	      
	public Object selectOne(String queryId){
		return sqlSession.selectOne(queryId);
	}
	      
	public Object selectOne(String queryId, Object params){
		return sqlSession.selectOne(queryId, params);
	}
	
}

