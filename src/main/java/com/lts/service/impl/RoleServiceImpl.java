package com.lts.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lts.dao.IFunctionDao;
import com.lts.dao.IRoleDao;
import com.lts.entity.Functions;
import com.lts.entity.Roles;
import com.lts.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;
	@Resource
	private IFunctionDao functionDao;
	
	@Override
	public List<Roles> findByRolePage(int start, int pageSize) {
		return roleDao.findByRolePage(start,pageSize);
	}

	@Override
	public int totalRoles() {
		return roleDao.totalRoles();
	}
	
	@Override
	public List<Roles> findRoleList() {
		return roleDao.findRoleList();
	}
	
	@Override
	public int addRole(Roles role) {
		return roleDao.addRole(role);
	}
	
	@Override
	public int deleteRole(int roleid) {
		return roleDao.deleteRole(roleid);
	}
	
	@Override
	public Roles findById(int roleid) {
		return roleDao.findById(roleid);
	}

	@Override
	public int updateRole(Roles role) {
		return roleDao.updateRole(role);
	}
	
	@Override
	public List<Functions> findFuncByRoleId(int roleid) {
		return functionDao.findFuncByRoleId(roleid);
	}

	@Override
	public int deleteByRoleId(int roleid) {
		return roleDao.deleteByRoleId(roleid);
	}

	@Override
	public int saveRoleFunction(int roleid, int functionid) {
		return roleDao.saveRoleFunction(roleid,functionid);
	}


	
}
