package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.ArticleDAO;
import com.dao.BbsDAO;
import com.dao.CartDAO;
import com.dao.CateDAO;
import com.dao.FavDAO;
import com.dao.GoodsDAO;
import com.dao.OrdersDAO;
import com.dao.RebbsDAO;
import com.dao.TopicDAO;
import com.dao.UsersDAO;
import com.entity.Article;
import com.entity.Bbs;
import com.entity.Cart;
import com.entity.Cate;
import com.entity.Fav;
import com.entity.Goods;
import com.entity.Orders;
import com.entity.Rebbs;
import com.entity.Topic;
import com.entity.Users;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/index")
public class IndexAction extends BaseAction {

	private BbsDAO bbsDAO;
	private UsersDAO usersDAO;
	private CateDAO cateDAO;
	private CartDAO cartDAO;
	private FavDAO favDAO;
	private OrdersDAO ordersDAO;
	private RebbsDAO rebbsDAO;
	private ArticleDAO articleDAO;
	private GoodsDAO goodsDAO;
	private TopicDAO topicDAO;

	// 公共方法 提供公共查询数据
	private void front(Map<String, Object> map) {
		getRequest().setAttribute("title", "电商");
		List<Cate> cateList = this.cateDAO.getAllCate();
		map.put("cateList", cateList);
		List<Goods> hotList = this.goodsDAO.getGoodsByHot();
		map.put("hotList", hotList);
	}

	// 首页显示的控制器
	@RequestMapping("index.action")
	public String index(Map<String, Object> map) {
		this.front(map);
		List<Cate> cateList = this.cateDAO.getAllCate();
		List<Cate> frontList = new ArrayList<Cate>();
		for (Cate cate : cateList) {
			List<Goods> goodsList = this.goodsDAO.getGoodsByCate(cate.getCateid());
			cate.setGoodsList(goodsList);
			frontList.add(cate);
		}
		map.put("frontList", frontList);
		List<Goods> newsList = this.goodsDAO.getGoodsByNews();
		map.put("newsList", newsList);
		List<Article> articleList = this.articleDAO.getFrontArticle();
		map.put("articleList", articleList);
		return "users/index";
	}

	// 按分类查询
	@RequestMapping("cate.action")
	public String cate(Map<String, Object> map, String id, String number) {
		this.front(map);
		Goods goods = new Goods();
		goods.setCateid(id);
		goods.setStatus("上架");
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getGoodsByCond(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		try {
			if (Integer.parseInt(number) < 0) {
				number = "0";
			}
		} catch (Exception ex) {
			number = "0";
		}
		if (Integer.parseInt(number) > maxPage) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		System.out.println(start);
		System.out.println(over);
		System.out.println(count);
		if (count <= 0) {
			over = pageNumber;
		}
		System.out.println(over);
		for (int i = start; i < over; i++) {
			Goods x = tempList.get(i);
			goodsList.add(x);
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
			buffer.append("<a href=\"index/cate.action?number=0&id=" + id + "\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (Integer.parseInt(number) - 1) + "&id=" + id
					+ "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (Integer.parseInt(number) + 1) + "&id=" + id
					+ "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (maxPage - 1) + "&id=" + id + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 全部商品
	@RequestMapping("all.action")
	public String all(Map<String, Object> map, String number) {
		this.front(map);
		Goods goods = new Goods();
		goods.setStatus("上架");
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getAllGoods();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		try {
			if (Integer.parseInt(number) < 0) {
				number = "0";
			}
		} catch (Exception ex) {
			number = "0";
		}
		if (Integer.parseInt(number) > maxPage) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		System.out.println(start);
		System.out.println(over);
		System.out.println(count);
		if (count <= 0) {
			over = pageNumber;
		}
		System.out.println(over);
		for (int i = start; i < over; i++) {
			Goods x = tempList.get(i);
			goodsList.add(x);
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
			buffer.append("<a href=\"index/all.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/all.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/all.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/all.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 新品上架
	@RequestMapping("news.action")
	public String news(Map<String, Object> map, String number) {
		this.front(map);
		List<Goods> goodsList = this.goodsDAO.getGoodsByNews();
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 查询商品
	@RequestMapping("query.action")
	public String query(Map<String, Object> map, String number, String name) {
		this.front(map);
		Goods goods = new Goods();
		goods.setGoodsname(name);
		goods.setStatus("上架");
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getGoodsByLike(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		try {
			if (Integer.parseInt(number) < 0) {
				number = "0";
			}
		} catch (Exception ex) {
			number = "0";
		}
		if (Integer.parseInt(number) > maxPage) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		System.out.println(start);
		System.out.println(over);
		System.out.println(count);
		if (count <= 0) {
			over = pageNumber;
		}
		System.out.println(over);
		for (int i = start; i < over; i++) {
			Goods x = tempList.get(i);
			goodsList.add(x);
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
			buffer.append("<a href=\"index/query.action?number=0&name=" + name + "\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/query.action?number=" + (Integer.parseInt(number) - 1) + "&name=" + name
					+ "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/query.action?number=" + (Integer.parseInt(number) + 1) + "&name=" + name
					+ "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (maxPage - 1) + "&name=" + name + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/list";
	}

	// 商品详情
	@RequestMapping("detail.action")
	public String detail(Map<String, Object> map, String id) {
		this.front(map);
		Goods goods = this.goodsDAO.getGoodsById(id);
		goods.setHits("" + (Integer.parseInt(goods.getHits()) + 1));
		this.goodsDAO.updateGoods(goods);
		map.put("goods", goods);
		Topic topic = new Topic();
		topic.setGoodsid(id);
		List<Topic> topicList = this.topicDAO.getTopicByCond(topic);
		map.put("topicList", topicList);
		map.put("tnum", topicList.size());
		return "users/detail";
	}

	@RequestMapping("addTopic.action")
	public String addTopic(Map<String, Object> map) {
		this.front(map);
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Topic topic = new Topic();
		topic.setTopicid(VeDate.getStringDatex());
		topic.setAddtime(VeDate.getStringDateShort());
		topic.setContents(this.getRequest().getParameter("contents"));
		topic.setGoodsid(this.getRequest().getParameter("goodsid"));
		topic.setNum(this.getRequest().getParameter("num"));
		topic.setUsersid(userid);
		this.topicDAO.insertTopic(topic);
		return "redirect:/index/detail.action?id=" + topic.getGoodsid();
	}

	// 商城公告
	@RequestMapping("article.action")
	public String article(Map<String, Object> map) {
		this.front(map);
		List<Article> articleList = this.articleDAO.getAllArticle();
		map.put("articleList", articleList);
		return "users/article";
	}

	@RequestMapping("read.action")
	public String read(Map<String, Object> map, String id) {
		this.front(map);
		Article article = this.articleDAO.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleDAO.updateArticle(article);
		map.put("article", article);
		return "users/read";
	}

	// 添加收藏
	@RequestMapping("addfav.action")
	public String addfav(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setFavid(VeDate.getStringDatex());
		fav.setAddtime(VeDate.getStringDateShort());
		fav.setGoodsid(getRequest().getParameter("id"));
		fav.setUsersid(userid);
		this.favDAO.insertFav(fav);
		return "redirect:/index/myfav.action";
	}

	// 我的收藏
	@RequestMapping("myfav.action")
	public String myfav(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setUsersid(userid);
		List<Fav> favList = this.favDAO.getFavByCond(fav);
		map.put("favList", favList);
		return "users/myfav";
	}

	// 删除收藏
	@RequestMapping("deletefav.action")
	public String deletefav(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.favDAO.deleteFav(getRequest().getParameter("id"));
		return "redirect:/index/myfav.action";
	}

	// 准备发布商品
	@RequestMapping("preGoods.action")
	public String preGoods(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/addgoods";
	}

	// 发布商品
	@RequestMapping("addgoods.action")
	public String addgoods(Map<String, Object> map, Goods goods) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		goods.setAddtime(VeDate.getStringDateShort());
		goods.setHits("0");
		goods.setUsersid(userid);
		goods.setGoodsid(VeDate.getStringDatex());
		goods.setStatus("上架");
		this.goodsDAO.insertGoods(goods);
		return "redirect:/index/preGoods.action";
	}

	// 我的商品
	@RequestMapping("mygoods.action")
	public String mygoods(Map<String, Object> map, String number) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Goods goods = new Goods();
		goods.setUsersid(userid);
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getGoodsByCond(goods);
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
			Goods goods1 = tempList.get(i);
			goodsList.add(goods1);
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
			buffer.append("<a href=\"index/mygoods.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/mygoods.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/mygoods.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/mygoods.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "users/mygoods";
	}

	// 准备编辑商品
	@RequestMapping("getGoodsById.action")
	public String getGoodsById(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		map.put("goods", this.goodsDAO.getGoodsById(getRequest().getParameter("id")));
		return "users/editgoods";
	}

	// 编辑商品 updateGoods
	@RequestMapping("updateGoods.action")
	public String updateGoods(Map<String, Object> map, Goods goods) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.goodsDAO.updateGoods(goods);
		return "redirect:/index/mygoods.action";
	}

	// 删除商品
	@RequestMapping("deletegoods.action")
	public String deletegoods(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.goodsDAO.deleteGoods(getRequest().getParameter("id"));
		return "redirect:/index/mygoods.action";
	}

	// 上架下架
	@RequestMapping("status.action")
	public String status(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		Goods goods = this.goodsDAO.getGoodsById(getRequest().getParameter("id"));
		String status = "上架";
		if (goods.getStatus().equals(status)) {
			status = "下架";
		}
		goods.setStatus(status);
		this.goodsDAO.updateGoods(goods);
		return "redirect:/index/mygoods.action";
	}

	// 添加商品到购物车
	@RequestMapping("addcart.action")
	public String addcart(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setCartid(VeDate.getStringDatex());
		cart.setAddtime(VeDate.getStringDateShort());
		cart.setGoodsid(getRequest().getParameter("goodsid"));
		cart.setPrice(getRequest().getParameter("price"));
		cart.setUsersid(userid);
		cart.setNum(getRequest().getParameter("num"));
		this.cartDAO.insertCart(cart);
		return "redirect:/index/cart.action";
	}

	// 查看购物车
	@RequestMapping("cart.action")
	public String cart(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartDAO.getCartByCond(cart);
		map.put("cartList", cartList);
		return "users/cart";
	}

	// 删除购物车中的商品
	@RequestMapping("deletecart.action")
	public String deletecart(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.cartDAO.deleteCart(id);
		return "redirect:/index/cart.action";
	}

	// 准备结算
	@RequestMapping("preCheckout.action")
	public String preCheckout(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartDAO.getCartByCond(cart);
		if (cartList.size() == 0) {
			getRequest().getSession().setAttribute("message", "请选购商品");
			return "redirect:/index/cart.action";
		}
		return "users/checkout";
	}

	// 结算
	@RequestMapping("checkout.action")
	public String checkout(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Cart cart1 = new Cart();
		cart1.setUsersid(userid);
		List<Cart> cartList = this.cartDAO.getCartByCond(cart1);
		if (cartList.size() == 0) {
			getRequest().getSession().setAttribute("message", "请选购商品");
			return "redirect:/index/cart.action";
		} else {
			// 获取一个1000-9999的随机数 防止同时提交
			Random rand = new Random();
			String ordercode = "PD" + VeDate.getStringDatex() + (rand.nextInt(900) + 100);
			for (Cart cart : cartList) {
				Goods goods = this.goodsDAO.getGoodsById(cart.getGoodsid());
				Orders orders = new Orders();
				orders.setAddress(getRequest().getParameter("address"));
				orders.setAddtime(VeDate.getStringDateShort());
				orders.setContact(getRequest().getParameter("contact"));
				orders.setOrdercode(ordercode);
				orders.setOrdersid(UUID.randomUUID().toString().replace("-", ""));
				orders.setReceiver(getRequest().getParameter("receiver"));
				orders.setStatus("未付款");
				orders.setUsersid(userid);
				orders.setSellerid(goods.getUsersid());
				orders.setNum(cart.getNum());
				orders.setGoodsid(goods.getGoodsid());
				orders.setPrice(goods.getPrice());
				this.ordersDAO.insertOrders(orders);
				this.cartDAO.deleteCart(cart.getCartid());
			}
		}
		return "redirect:/index/showOrders.action";
	}

	// 查看订购
	@RequestMapping("showOrders.action")
	public String showOrders(String number, Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Orders orders = new Orders();
		orders.setUsersid(userid);
		List<Orders> ordersList = new ArrayList<Orders>();
		List<Orders> tempList = this.ordersDAO.getOrdersByCond(orders);
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
			Orders o = tempList.get(i);
			ordersList.add(o);
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
			buffer.append("<a href=\"index/showOrders.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("ordersList", ordersList);
		return "users/orderlist";
	}

	// 查看订购
	@RequestMapping("mysell.action")
	public String mysell(String number, Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Orders orders = new Orders();
		orders.setSellerid(userid);
		List<Orders> ordersList = new ArrayList<Orders>();
		List<Orders> tempList = this.ordersDAO.getOrdersByCond(orders);
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
			Orders o = tempList.get(i);
			ordersList.add(o);
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
			buffer.append("<a href=\"index/showOrders.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("ordersList", ordersList);
		return "users/mysell";
	}

	// 准备付款
	@RequestMapping("prePay.action")
	public String prePay(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		map.put("id", id);
		return "users/pay";
	}

	// 付款
	@RequestMapping("pay.action")
	public String pay(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		Orders orders = this.ordersDAO.getOrdersById(getRequest().getParameter("id"));
		orders.setStatus("已付款");
		this.ordersDAO.updateOrders(orders);
		return "redirect:/index/showOrders.action";
	}

	// 确认收货
	@RequestMapping("over.action")
	public String over(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		Orders orders = this.ordersDAO.getOrdersById(getRequest().getParameter("id"));
		orders.setStatus("已收货");
		this.ordersDAO.updateOrders(orders);
		return "redirect:/index/showOrders.action";
	}

	// 确认收货
	@RequestMapping("send.action")
	public String send(Map<String, Object> map, String id) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		Orders orders = this.ordersDAO.getOrdersById(getRequest().getParameter("id"));
		orders.setStatus("已发货");
		this.ordersDAO.updateOrders(orders);
		Goods goods = this.goodsDAO.getGoodsById(orders.getGoodsid());
		goods.setStorage("" + (Integer.parseInt(goods.getStorage()) - Integer.parseInt(orders.getNum())));
		this.goodsDAO.updateGoods(goods);
		return "redirect:/index/mysell.action";
	}

	// 准备登录
	@RequestMapping("preLogin.action")
	public String preLogin(Map<String, Object> map) {
		this.front(map);
		return "users/login";
	}

	// 用户登录
	@RequestMapping("login.action")
	public String login(Map<String, Object> map) {
		this.front(map);
		String username = getRequest().getParameter("username");
		String password = getRequest().getParameter("password");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersDAO.getUsersByCond(u);
		if (usersList.size() == 0) {
			getRequest().setAttribute("message", "用户名不存在");
			return "redirect:/index/preLogin.action";
		} else {
			Users users = usersList.get(0);
			if (users.getStatus().equals("锁定")) {
				getRequest().getSession().setAttribute("message", "账户被锁定");
				return "redirect:/index/preLogin.action";
			}
			if (password.equals(users.getPassword())) {
				getRequest().getSession().setAttribute("userid", users.getUsersid());
				getRequest().getSession().setAttribute("username", users.getUsername());
				getRequest().getSession().setAttribute("users", users);
			} else {
				getRequest().setAttribute("message", "密码错误");
				return "redirect:/index/preLogin.action";
			}
		}
		return "redirect:/index/usercenter.action";
	}

	// 准备注册
	@RequestMapping("preReg.action")
	public String preReg(Map<String, Object> map) {
		this.front(map);
		return "users/register";
	}

	// 用户注册
	@RequestMapping("register.action")
	public String register(Users users, Map<String, Object> map) {
		this.front(map);
		Users u = new Users();
		u.setUsername(users.getUsername());
		List<Users> usersList = this.usersDAO.getUsersByCond(users);
		if (usersList.size() == 0) {
			users.setUsersid(VeDate.getStringDatex());
			users.setRegdate(VeDate.getStringDateShort());
			users.setStatus("锁定");
			this.usersDAO.insertUsers(users);
		} else {
			getRequest().setAttribute("message", "用户名已存在");
			return "redirect:/index/preReg.action";
		}
		return "redirect:/index/preLogin.action";
	}

	// 退出登录
	@RequestMapping("exit.action")
	public String exit(Map<String, Object> map) {
		this.front(map);
		getRequest().getSession().removeAttribute("userid");
		getRequest().getSession().removeAttribute("username");
		getRequest().getSession().removeAttribute("users");
		return "index";
	}

	// 用户中心
	@RequestMapping("usercenter.action")
	public String usercenter(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/usercenter";
	}

	// 用户中心
	@RequestMapping("userinfo.action")
	public String userinfo(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/userinfo";
	}

	// 修改个人信息
	@RequestMapping("personal.action")
	public String personal(Map<String, Object> map, Users users) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.usersDAO.updateUsers(users);
		getRequest().getSession().setAttribute("users", users);
		return "redirect:/index/userinfo.action";
	}

	// 准备修改密码
	@RequestMapping("prePwd.action")
	public String prePwd(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/editpwd";
	}

	// 修改密码
	@RequestMapping("editpwd.action")
	public String editpwd(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		String password = getRequest().getParameter("password");
		String repassword = getRequest().getParameter("repassword");
		Users users = this.usersDAO.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			this.usersDAO.updateUsers(users);
		} else {
			getRequest().setAttribute("message", "旧密码错误");
			return "redirect:/index/prePwd.action";
		}
		return "redirect:/index/prePwd.action";
	}

	// 留言板
	@RequestMapping("bbs.action")
	public String bbs(Map<String, Object> map) {
		this.front(map);
		List<Bbs> bbsList = this.bbsDAO.getAllBbs();
		map.put("bbsList", bbsList);
		return "users/bbs";
	}

	// 发布留言
	@RequestMapping("addbbs.action")
	public String addbbs(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Bbs bbs = new Bbs();
		bbs.setBbsid(VeDate.getStringDatex());
		bbs.setAddtime(VeDate.getStringDate());
		bbs.setContents(getRequest().getParameter("contents"));
		bbs.setHits("0");
		bbs.setRepnum("0");
		bbs.setTitle(getRequest().getParameter("title"));
		bbs.setUsersid(userid);
		this.bbsDAO.insertBbs(bbs);
		return "redirect:/index/bbs.action";
	}

	// 查看留言
	@RequestMapping("readbbs.action")
	public String readbbs(Map<String, Object> map) {
		this.front(map);
		Bbs bbs = this.bbsDAO.getBbsById(getRequest().getParameter("id"));
		bbs.setHits("" + (Integer.parseInt(bbs.getHits()) + 1));
		this.bbsDAO.updateBbs(bbs);
		map.put("bbs", bbs);
		Rebbs rebbs = new Rebbs();
		rebbs.setBbsid(bbs.getBbsid());
		List<Rebbs> rebbsList = this.rebbsDAO.getRebbsByCond(rebbs);
		map.put("rebbsList", rebbsList);
		return "users/readbbs";
	}

	// 回复留言
	@RequestMapping("rebbs.action")
	public String rebbs(Map<String, Object> map) {
		this.front(map);
		if (getRequest().getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) getRequest().getSession().getAttribute("userid");
		Rebbs rebbs = new Rebbs();
		rebbs.setRebbsid(VeDate.getStringDatex());
		rebbs.setAddtime(VeDate.getStringDate());
		rebbs.setContents(getRequest().getParameter("contents"));
		rebbs.setBbsid(getRequest().getParameter("bbsid"));
		rebbs.setUsersid(userid);
		this.rebbsDAO.insertRebbs(rebbs);
		Bbs bbs = this.bbsDAO.getBbsById(rebbs.getBbsid());
		bbs.setRepnum("" + (Integer.parseInt(bbs.getRepnum()) + 1));
		this.bbsDAO.updateBbs(bbs);
		String path = "redirect:/index/readbbs.action?id=" + bbs.getBbsid();
		return path;
	}

	public BbsDAO getBbsDAO() {
		return bbsDAO;
	}

	public void setBbsDAO(BbsDAO bbsDAO) {
		this.bbsDAO = bbsDAO;
	}

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public CateDAO getCateDAO() {
		return cateDAO;
	}

	public void setCateDAO(CateDAO cateDAO) {
		this.cateDAO = cateDAO;
	}

	public CartDAO getCartDAO() {
		return cartDAO;
	}

	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	public FavDAO getFavDAO() {
		return favDAO;
	}

	public void setFavDAO(FavDAO favDAO) {
		this.favDAO = favDAO;
	}

	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}

	public RebbsDAO getRebbsDAO() {
		return rebbsDAO;
	}

	public void setRebbsDAO(RebbsDAO rebbsDAO) {
		this.rebbsDAO = rebbsDAO;
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	public TopicDAO getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

}
