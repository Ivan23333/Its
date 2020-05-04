package com.lts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Roles;

public interface IRoleDao {
	
	List<Roles> findByRolePage(@Param("start")int start, @Param("pageSize")int pageSize);

	int totalRoles();
	
	List<Roles> findRoleByUserId(@Param("userid")int userid);
	
	List<Roles> findRoleList();
	
	int addRole(Roles role);

	int deleteRole(@Param("roleid")int roleid);
	
	Roles findById(@Param("roleid")int roleid);

	int updateRole(Roles role);
	
	int deleteByRoleId(int roleid);

	int saveRoleFunction(@Param("roleid")int roleid, @Param("functionid")int functionid);
	
}
