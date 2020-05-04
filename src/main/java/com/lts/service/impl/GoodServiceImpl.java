package com.lts.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lts.dao.IGoodDao;
import com.lts.entity.Detail;
import com.lts.entity.Goods;
import com.lts.service.IGoodService;

@Service("goodsService")
public class GoodServiceImpl implements IGoodService {
	
	@Resource
	private IGoodDao goodDao;

	@Override
	public List<Goods> findMyGoodsByPage(int start, int pageSize,int userid) {
		return goodDao.findMyGoodsByPage(start,pageSize,userid);
	}

	@Override
	public int countMyGoods(int userid) {
		return goodDao.countMyGoods(userid);
	}
	
	@Override
	public List<Goods> findTransportGoodsByPage(int start, int pageSize,int userid) {
		return goodDao.findTransportGoodsByPage(start,pageSize,userid);
	}

	@Override
	public int countTransportGoods(int userid) {
		return goodDao.countTransportGoods(userid);
	}
	
	@Override
	public int deleteGoods(int goodsid) {
		return goodDao.deleteGoods(goodsid);
	}
		
	@Override
	public Goods findById(int goodsid) {
		return goodDao.findById(goodsid);
	}

	@Override
	public int updateGoods(Goods goods) {
		return goodDao.updateGoods(goods);
	}
	
	@Override
	public Detail findDetail(int goodsid) {
		return goodDao.findDetail(goodsid);
	}

	@Override
	public List<String> findKind() {
		return goodDao.findKind();
	}
	
	@Override
	public int addGoods(Goods goods) {
		return goodDao.addGoods(goods);
	}
	
	@Override
	public int addDetail(Detail detail) {
		return goodDao.addDetail(detail);
	}
	
	@Override
	public int updateDetail(Detail detail) {
		return goodDao.updateDetail(detail);
	}

}
