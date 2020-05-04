package com.lts.service;

import java.util.List;

import com.lts.entity.Functions;
import com.lts.entity.Roles;

public interface IRoleService {

	List<Roles> findByRolePage(int start, int pageSize);

	int totalRoles();
	
	List<Roles> findRoleList();
	
	int addRole(Roles role);
	
	int deleteRole(int roleid);
	
	public Roles findById(int roleid);

	int updateRole(Roles role);
	
	List<Functions> findFuncByRoleId(int roleid);

	int deleteByRoleId(int roleid);

	int saveRoleFunction(int roleid, int functionid);

}

