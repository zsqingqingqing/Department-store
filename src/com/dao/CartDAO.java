package com.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import com.entity.Cart;

public class CartDAO {
	// sqlSessionTemplate 注入 在applicationContext.xml里定义
	private SqlSessionTemplate sqlSessionTemplate;

	// 插入数据 调用entity包cart.xml里的insertCart配置
	public void insertCart(Cart cart) {
		this.sqlSessionTemplate.insert("insertCart", cart);
	}

	// 更新数据 调用entity包cart.xml里的updateCart配置
	public void updateCart(Cart cart) {
		this.sqlSessionTemplate.update("updateCart", cart);
	}

	// 删除数据 调用entity包cart.xml里的deleteCart配置
	public void deleteCart(String cartid) {
		this.sqlSessionTemplate.delete("deleteCart", cartid);
	}

	// 查询全部数据 调用entity包cart.xml里的getAllCart配置
	public List<Cart> getAllCart() {
		return this.sqlSessionTemplate.selectList("getAllCart");
	}

	// 按照Admin类里面的值精确查询 调用entity包cart.xml里的getCartByCond配置
	public List<Cart> getCartByCond(Cart cart) {
		return this.sqlSessionTemplate.selectList("getCartByCond", cart);
	}

	// 按照Cart类里面的值模糊查询 调用entity包cart.xml里的getCartByLike配置
	public List<Cart> getCartByLike(Cart cart) {
		return this.sqlSessionTemplate.selectList("getCartByLike", cart);
	}

	// 按主键查询表返回单一的Cart实例 调用entity包cart.xml里的getCartById配置
	public Cart getCartById(String cartid) {
		return this.sqlSessionTemplate.selectOne("getCartById", cartid);
	}

	// IOC注入所需要的getter和setter
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
