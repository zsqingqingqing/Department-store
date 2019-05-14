package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Goods;
import com.dao.GoodsDAO;
import com.entity.Users;
import com.entity.Cate;
import com.dao.UsersDAO;
import com.dao.CateDAO;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/goods")
public class GoodsAction extends BaseAction {
	// 注入AdminDAO 并getter setter
	private GoodsDAO goodsDAO;
	private UsersDAO usersDAO;
	private CateDAO cateDAO;

	// 准备添加数据
	@RequestMapping("createGoods.action")
	public String createGoods(Map<String, Object> map) {
		List<Users> usersList = this.usersDAO.getAllUsers();
		map.put("usersList", usersList);
		List<Cate> cateList = this.cateDAO.getAllCate();
		map.put("cateList", cateList);
		return "admin/addgoods";
	}

	// 添加数据
	@RequestMapping("addGoods.action")
	public String addGoods(Goods goods) {
		goods.setGoodsid(VeDate.getStringDatex());
		this.goodsDAO.insertGoods(goods);
		return "redirect:/goods/createGoods.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteGoods.action")
	public String deleteGoods(String id) {
		this.goodsDAO.deleteGoods(id);
		return "redirect:/goods/getAllGoods.action";
	}

	@RequestMapping("status.action")
	public String status(String id, Map<String, Object> map) {
		Goods goods = this.goodsDAO.getGoodsById(id);
		String status = "下架";
		if (status.equals(goods.getStatus())) {
			status = "上架";
		}
		goods.setStatus(status);
		this.goodsDAO.updateGoods(goods);
		return "redirect:/goods/getAllGoods.action";
	}

	// 更新数据
	@RequestMapping("updateGoods.action")
	public String updateGoods(Goods goods) {
		this.goodsDAO.updateGoods(goods);
		return "redirect:/goods/getAllGoods.action";
	}

	// 显示全部数据
	@RequestMapping("getAllGoods.action")
	public String getAllGoods(String number, Map<String, Object> map) {
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Goods> tempList = new ArrayList<Goods>();
		tempList = this.goodsDAO.getAllGoods();
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
			Goods goods = tempList.get(i);
			goodsList.add(goods);
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
			buffer.append("<a href=\"goods/getAllGoods.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"goods/getAllGoods.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"goods/getAllGoods.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"goods/getAllGoods.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		map.put("html", html);
		map.put("goodsList", goodsList);
		return "admin/listgoods";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryGoodsByCond.action")
	public String queryGoodsByCond(String cond, String name, Map<String, Object> map) {
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods goods = new Goods();
		if (cond != null) {
			if ("usersid".equals(cond)) {
				goods.setUsersid(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("goodsname".equals(cond)) {
				goods.setGoodsname(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("cateid".equals(cond)) {
				goods.setCateid(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("image".equals(cond)) {
				goods.setImage(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("marketprice".equals(cond)) {
				goods.setMarketprice(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("price".equals(cond)) {
				goods.setPrice(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("addtime".equals(cond)) {
				goods.setAddtime(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("hits".equals(cond)) {
				goods.setHits(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("status".equals(cond)) {
				goods.setStatus(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
			if ("contents".equals(cond)) {
				goods.setContents(name);
				goodsList = this.goodsDAO.getGoodsByLike(goods);
			}
		}
		map.put("goodsList", goodsList);
		return "admin/querygoods";
	}

	// 按主键查询数据
	@RequestMapping("getGoodsById.action")
	public String getGoodsById(String id, Map<String, Object> map) {
		Goods goods = this.goodsDAO.getGoodsById(id);
		map.put("goods", goods);
		List<Users> usersList = this.usersDAO.getAllUsers();
		map.put("usersList", usersList);
		List<Cate> cateList = this.cateDAO.getAllCate();
		map.put("cateList", cateList);
		return "admin/editgoods";
	}

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
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
}
