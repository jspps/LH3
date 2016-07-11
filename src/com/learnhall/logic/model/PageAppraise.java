package com.learnhall.logic.model;

import java.util.List;
import java.util.Map;

import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Appraise;
import com.learnhall.db.entity.AppraiseEntity;

/**
 * 分页对象-评论
 * 
 * @author hxw
 * 
 */
public class PageAppraise extends APage<Appraise> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return AppraiseEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Appraise> getList(Map<String, Object> params, int page,
			int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			return AppraiseEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
		}
		return null;
	}
}
