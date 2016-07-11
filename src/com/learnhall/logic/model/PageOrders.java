package com.learnhall.logic.model;

import java.util.List;
import java.util.Map;

import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Orders;
import com.learnhall.db.entity.OrdersEntity;

/**
 * 分页对象-订单
 * 
 * @author hxw
 * 
 */
public class PageOrders extends APage<Orders> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return OrdersEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Orders> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return OrdersEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
