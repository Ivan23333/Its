package com.lts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Functions;

public interface IFunctionDao {

	public List<Functions> findMenu(@Param("userid")int userid);

	public List<Functions> findFunctionList();
	
	public List<Functions> findFuncByRoleId(int roleid);
	
	public int addFunction(Functions function);
	
	public int deleteFunction(@Param("id") String[] id);
	
	public Functions findById(@Param("functionid")int functionid);
	
	public int updateFunction(Functions function);

}
