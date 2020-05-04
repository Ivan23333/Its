package com.lts.service;

import java.util.List;

import com.lts.entity.Roles;
import com.lts.entity.Users;

public interface IUserService {

	public List<Users> findAll();
	
	public Users login(String username,String password);

	public List<Users> findByPage(int start, int pageSize);
	
	public int countUser();

	public int addUser(Users users);
	
	public int deleteUser(int userid);
	
	public Users findById(int userid);
	
	public int updateUser(Users users);
	
	public List<Roles> findRoleByUserId(int userid);
	
	public int deleteByUserId(int userid);

	public int saveUserRole(int userid, int roleid);
	
}
