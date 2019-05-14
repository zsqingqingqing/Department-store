package com.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import com.entity.Goods;

public class GoodsDAO {
	// sqlSessionTemplate 注入 在applicationContext.xml里定义
	private SqlSessionTemplate sqlSessionTemplate;

	// 插入数据 调用entity包goods.xml里的insertGoods配置
	public void insertGoods(Goods goods) {
		this.sqlSessionTemplate.insert("insertGoods", goods);
	}

	// 更新数据 调用entity包goods.xml里的updateGoods配置
	public void updateGoods(Goods goods) {
		this.sqlSessionTemplate.update("updateGoods", goods);
	}

	// 删除数据 调用entity包goods.xml里的deleteGoods配置
	public void deleteGoods(String goodsid) {
		this.sqlSessionTemplate.delete("deleteGoods", goodsid);
	}

	// 查询全部数据 调用entity包goods.xml里的getAllGoods配置
	public List<Goods> getAllGoods() {
		return this.sqlSessionTemplate.selectList("getAllGoods");
	}

	public List<Goods> getGoodsByNews() {
		return this.sqlSessionTemplate.selectList("getGoodsByNews");
	}

	public List<Goods> getGoodsByHot() {
		return this.sqlSessionTemplate.selectList("getGoodsByHot");
	}

	public List<Goods> getGoodsByCate(String cateid) {
		return this.sqlSessionTemplate.selectList("getGoodsByCate", cateid);
	}

	// 按照Admin类里面的值精确查询 调用entity包goods.xml里的getGoodsByCond配置
	public List<Goods> getGoodsByCond(Goods goods) {
		return this.sqlSessionTemplate.selectList("getGoodsByCond", goods);
	}

	// 按照Goods类里面的值模糊查询 调用entity包goods.xml里的getGoodsByLike配置
	public List<Goods> getGoodsByLike(Goods goods) {
		return this.sqlSessionTemplate.selectList("getGoodsByLike", goods);
	}

	// 按主键查询表返回单一的Goods实例 调用entity包goods.xml里的getGoodsById配置
	public Goods getGoodsById(String goodsid) {
		return this.sqlSessionTemplate.selectOne("getGoodsById", goodsid);
	}

	// IOC注入所需要的getter和setter
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
