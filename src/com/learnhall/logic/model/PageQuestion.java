package com.learnhall.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Answer;
import com.learnhall.db.bean.Ask;
import com.learnhall.db.bean.Record4seeanswer;
import com.learnhall.db.entity.AnswerEntity;
import com.learnhall.db.entity.AskEntity;
import com.learnhall.db.entity.Record4seeanswerEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageQuestion extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return AskEntity.getCountAllBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {

		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;

			List<Ask> list = AskEntity.getListBy(params, begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> ret = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					ret.add(list.get(i).toBasicMap());
				}
				return ret;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*** 回复了别人的问题时候,查看自己的回复 **/
	public void init4CustAnswer(int custid, List<Map> list) {
		if (custid <= 0 || ListEx.isEmpty(list)) {
			return;
		}
		int lens = list.size();
		for (int i = 0; i < lens; i++) {
			Map map = list.get(i);
			int id = MapEx.getInt(map, "id");
			Answer answer = AnswerEntity.getByAskidCustomerid(id, custid);
			if (answer != null) {
				map.put("answer", answer.getContent());
			}
		}
	}

	/*** 查看满意答案的 **/
	public void init4SatisfiedAnswer(int custid, List<Map> list) {
		if (ListEx.isEmpty(list)) {
			return;
		}
		int lens = list.size();
		for (int i = 0; i < lens; i++) {
			Map map = list.get(i);
			int askid = MapEx.getInt(map, "id");
			int custid4Ask = MapEx.getInt(map, "customerid");

			if (custid != custid4Ask) {
				Record4seeanswer record = Record4seeanswerEntity
						.getByAskidCustid(askid, custid);
				if (record == null) {
					continue;
				}
			}

			int answerid = MapEx.getInt(map, "answerid");
			Answer answer = AnswerEntity.getByKey(answerid);
			if (answer != null) {
				map.put("answer", answer.getContent());
			}
		}
	}
}
