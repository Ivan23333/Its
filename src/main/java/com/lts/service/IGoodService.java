package com.lts.service;

import java.util.List;

import com.lts.entity.Detail;
import com.lts.entity.Goods;


public interface IGoodService {
	
	public List<Goods> findMyGoodsByPage(int start, int pageSize,int userid);
	
	public int countMyGoods(int userid);
	
	public List<Goods> findTransportGoodsByPage(int start, int pageSize,int userid);
	
	public int countTransportGoods(int userid);
	
	int deleteGoods(int goodsid);
	
	public Goods findById(int goodsid);

	int updateGoods(Goods goods);
	
	public Detail findDetail(int goodsid);
	
	public List<String> findKind();
	
	public int addGoods(Goods goods);
	
	public int addDetail(Detail detail);
	
	int updateDetail(Detail detail);

}
