package com.learnhall.logic.model;

import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Boughtkinds;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.entity.BoughtkindsEntity;

public class PageBuyCourses extends APage<Boughtkinds> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return BoughtkindsEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Boughtkinds> getList(Map<String, Object> params, int page,
			int pageSize) {
		int begin = (page - 1) * pageSize;
		int limit = pageSize;
		begin = begin < 0 ? 0 : begin;
		try {
			List<Boughtkinds> list = BoughtkindsEntity.getListBy(params, begin,
					limit);
			repair(list);
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	// 修复下以前购买的套餐信息
	boolean isRepair = true;

	public void repair(List<Boughtkinds> list) {
		if (!isRepair)
			return;

		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			Boughtkinds tmp;
			Kind kind;
			String name;
			double price;
			boolean isUp = false;
			for (int i = 0; i < lens; i++) {
				tmp = list.get(i);
				kind = tmp.getKindFkKindid();
				isUp = false;
				if (kind != null) {
					name = kind.getNmProduct();
					price = kind.getPrice();
					if (price != tmp.getPrice()) {
						tmp.setPrice(price);
						isUp = true;
					}
					if (!name.equals(tmp.getName())) {
						tmp.setName(name);
						isUp = true;
					}
				}
				if (isUp) {
					tmp.update();
				}
			}
		}
	}
}
