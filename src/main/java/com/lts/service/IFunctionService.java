package com.lts.service;

import java.util.List;

import com.lts.entity.Functions;
import com.lts.entity.MenuVo;

public interface IFunctionService {

	public List<MenuVo> findMenu(int userid);

	public List<Functions> findFunctionList();
	
	public int addFunction(Functions function);
	
	public int deleteFunction(String[] id);
	
	public Functions findById(int functionid);
	
	public int updateFunction(Functions function);

}
