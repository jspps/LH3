package com.learnhall.logic.model;

import java.util.List;
import java.util.Map;

import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Recordanswer;
import com.learnhall.db.entity.RecordanswerEntity;

public class PageRecordAnswer extends APage<Recordanswer> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return RecordanswerEntity.getCountAllBy(params);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Recordanswer> getList(Map<String, Object> params, int page,
			int pageSize) {
		int begin = (page - 1) * pageSize;
		int limit = pageSize;
		begin = begin < 0 ? 0 : begin;
		try {
			return RecordanswerEntity.getListBy(params, begin, limit);
		} catch (Exception e) {
			return null;
		}
	}

}
