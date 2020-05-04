package com.lts.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lts.dao.IRoleDao;
import com.lts.dao.IUserDao;
import com.lts.entity.Roles;
import com.lts.entity.Users;
import com.lts.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	@Resource
	private IRoleDao roleDao;
	
	public List<Users> findAll() {
		return userDao.findAll();
	}

	@Override
	public Users login(String username, String password) {
		
		return userDao.login(username,password);
	}

	@Override
	public List<Users> findByPage(int start, int pageSize) {
		return userDao.findByPage(start,pageSize);
	}

	@Override
	public int countUser() {
		return userDao.countUser();
	}

	@Override
	public int addUser(Users users) {
		return userDao.addUser(users);
		
	}
	
	public int deleteUser(int userid) {
		return userDao.deleteUser(userid);
	}
	
	@Override
	public Users findById(int userId) {
		return userDao.findById(userId);
	}

	@Override
	public int updateUser(Users users) {
		return userDao.updateUser(users);
	}
	
	@Override
	public List<Roles> findRoleByUserId(int userid) {
		return roleDao.findRoleByUserId(userid);
	}
	
	@Override
	public int deleteByUserId(int userid) {
		return userDao.deleteByUserId(userid);
	}

	@Override
	public int saveUserRole(int userid, int roleid) {
		return userDao.saveUserRole(userid,roleid);
	}
}
