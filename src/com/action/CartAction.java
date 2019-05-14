package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Cart;
import com.dao.CartDAO;
import com.entity.Users;
import com.entity.Goods;
import com.dao.UsersDAO;
import com.dao.GoodsDAO;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/cart")
public class CartAction extends BaseAction {
	// 注入AdminDAO 并getter setter
	private CartDAO cartDAO;
	private UsersDAO usersDAO;
	private GoodsDAO goodsDAO;

	// 准备添加数据
	@RequestMapping("createCart.action")
	public String createCart(Map<String, Object> map) {
		List<Users> usersList = this.usersDAO.getAllUsers();
		map.put("usersList", usersList);
		List<Goods> goodsList = this.goodsDAO.getAllGoods();
		map.put("goodsList", goodsList);
		return "admin/addcart";
	}

	// 添加数据
	@RequestMapping("addCart.action")
	public String addCart(Cart cart) {
		cart.setCartid(VeDate.getStringDatex());
		this.cartDAO.insertCart(cart);
		return "redirect:/cart/createCart.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteCart.action")
	public String deleteCart(String id) {
		this.cartDAO.deleteCart(id);
		return "redirect:/cart/getAllCart.action";
	}

	// 更新数据
	@RequestMapping("updateCart.action")
	public String updateCart(Cart cart) {
		this.cartDAO.updateCart(cart);
		return "redirect:/cart/getAllCart.action";
	}

	// 显示全部数据
	@RequestMapping("getAllCart.action")
	public String getAllCart(String number, Map<String, Object> map) {
		List<Cart> cartList = new ArrayList<Cart>();
		List<Cart> tempList = new ArrayList<Cart>();
		tempList = this.cartDAO.getAllCart();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Cart cart = tempList.get(i);
			cartList.add(cart);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"cart/getAllCart.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"cart/getAllCart.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"cart/getAllCart.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"cart/getAllCart.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("cartList", cartList);
		return "admin/listcart";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryCartByCond.action")
	public String queryCartByCond(String cond, String name, Map<String, Object> map) {
		List<Cart> cartList = new ArrayList<Cart>();
		Cart cart = new Cart();
		if (cond != null) {
			if ("usersid".equals(cond)) {
				cart.setUsersid(name);
				cartList = this.cartDAO.getCartByLike(cart);
			}
			if ("goodsid".equals(cond)) {
				cart.setGoodsid(name);
				cartList = this.cartDAO.getCartByLike(cart);
			}
			if ("price".equals(cond)) {
				cart.setPrice(name);
				cartList = this.cartDAO.getCartByLike(cart);
			}
			if ("addtime".equals(cond)) {
				cart.setAddtime(name);
				cartList = this.cartDAO.getCartByLike(cart);
			}
		}
		map.put("cartList", cartList);
		return "admin/querycart";
	}

	// 按主键查询数据
	@RequestMapping("getCartById.action")
	public String getCartById(String id, Map<String, Object> map) {
		Cart cart = this.cartDAO.getCartById(id);
		map.put("cart", cart);
		List<Users> usersList = this.usersDAO.getAllUsers();
		map.put("usersList", usersList);
		List<Goods> goodsList = this.goodsDAO.getAllGoods();
		map.put("goodsList", goodsList);
		return "admin/editcart";
	}

	public CartDAO getCartDAO() {
		return cartDAO;
	}

	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}
}
