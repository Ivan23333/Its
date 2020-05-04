package com.lts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Users;

public interface IUserDao {

	public List<Users> findAll();
	
	public Users login(@Param("username")String username,@Param("password")String password);

	public List<Users> findByPage(@Param("start")int start, @Param("pageSize")int pageSize);
	
	public int countUser();
	
	public int addUser(Users users);
	
	public int deleteUser(@Param("userid")int userid);
	
	public Users findById(@Param("userid")int userid);

	public int updateUser(Users users);

	public int deleteByUserId(int userid);

	public int saveUserRole(@Param("userid")int userid,@Param("roleid")int roleid);

}
