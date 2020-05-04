package com.lts.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lts.dao.IFunctionDao;
import com.lts.entity.Functions;
import com.lts.entity.MenuVo;
import com.lts.service.IFunctionService;

@Service("functionsService")
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	private IFunctionDao FunctionDao;
	
	@Override
	public List<MenuVo> findMenu(int userid) {
		List<MenuVo> list = new ArrayList<MenuVo>();
		MenuVo menuVo = null;
		List<Functions> listFunctions = FunctionDao.findMenu(userid);
		if(listFunctions != null && listFunctions.size() > 0) {
			for(Functions func : listFunctions) {
				menuVo = new MenuVo();
				menuVo.setId(String.valueOf(func.getFunctionid()));
				menuVo.setUrl(func.getFunctionurl());
				menuVo.setName(func.getFunctionname());
				
				if(func.getParentid() != null) {
					menuVo.setOpen(false);
					menuVo.setpId(func.getParentid().toString());
				}else {
					menuVo.setOpen(true);
				}
				menuVo.setChecked(false);
				
				list.add(menuVo);
			}
		}
		
		
		return list;
	}

	@Override
	public List<Functions> findFunctionList() {
		return FunctionDao.findFunctionList();
	}
	
	@Override
	public int addFunction(Functions function) {
		return FunctionDao.addFunction(function);
	}
	
	@Override
	public int deleteFunction(String[] id) {
		return FunctionDao.deleteFunction(id);
	}

	@Override
	public Functions findById(int functionid) {
		return FunctionDao.findById(functionid);
	}
	
	@Override
	public int updateFunction(Functions function) {
		return FunctionDao.updateFunction(function);
	}
	
}
