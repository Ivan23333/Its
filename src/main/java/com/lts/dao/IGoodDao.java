package com.lts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lts.entity.Detail;
import com.lts.entity.Goods;
public interface IGoodDao {
	
	public List<Goods> findMyGoodsByPage(@Param("start")int start, @Param("pageSize")int pageSize, @Param("userid")int userid);
	
	public int countMyGoods( @Param("userid")int userid);
	
	public List<Goods> findTransportGoodsByPage(@Param("start")int start, @Param("pageSize")int pageSize, @Param("userid")int userid);
	
	public int countTransportGoods( @Param("userid")int userid);
	
	int deleteGoods(@Param("goodsid")int goodsid);
	
	Goods findById(@Param("goodsid")int goodsid);

	int updateGoods(Goods goods);
	
	Detail findDetail(@Param("goodsid")int goodsid);
	
	public List<String> findKind();
	
	public int addGoods(Goods goods);
	
	public int addDetail(Detail detail);
	
	int updateDetail(Detail detail);

}	
