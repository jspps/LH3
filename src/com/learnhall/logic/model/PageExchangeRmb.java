package com.learnhall.logic.model;

import java.util.List;
import java.util.Map;

import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Exchangermb;
import com.learnhall.db.entity.ExchangermbEntity;

/**
 * 分页对象-消息
 * 
 * @author Canyon
 * 
 */
public class PageExchangeRmb extends APage<Exchangermb> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return ExchangermbEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Exchangermb> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return ExchangermbEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
